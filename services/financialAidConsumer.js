// services/financialAidConsumer.js
const amqp = require("amqplib");
const { EXCHANGE_NAME, QUEUES } = require("../config/rabbitmq");
const FinancialAid = require("../models/financialAid");

exports.startConsumer = async () => {
  try {
    const connection = await amqp.connect("amqp://localhost");
    const channel = await connection.createChannel();

    await channel.assertExchange(EXCHANGE_NAME, "topic", { durable: true });

    // Assert queue for financial aid updates
    const q = await channel.assertQueue(QUEUES.FINANCIAL_AID_UPDATES, {
      durable: true,
    });
    await channel.bindQueue(q.queue, EXCHANGE_NAME, "status.update");

    console.log("Financial Aid Consumer started...");

    channel.consume(q.queue, async (msg) => {
      if (msg.content) {
        const message = JSON.parse(msg.content.toString());
        console.log("Received:", message);

        // Process message here
        // Example: Update database based on message

        channel.ack(msg);
      }
    });
  } catch (error) {
    console.error("Consumer error:", error);
  }
};
