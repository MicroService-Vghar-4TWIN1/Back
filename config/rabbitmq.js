// config/rabbitmq.js
const amqp = require("amqplib");

const RABBITMQ_URL = process.env.RABBITMQ_URL || "amqp://localhost";
const EXCHANGE_NAME = "financial_aid_exchange";
const QUEUES = {
  STATUS_UPDATE: "financial_aid_status_updates",
  NEW_REQUEST: "new_financial_aid_requests",
};

let connection = null;
let channel = null;

async function connectRabbitMQ() {
  try {
    connection = await amqp.connect(RABBITMQ_URL);
    channel = await connection.createChannel();

    // Assert the exchange
    await channel.assertExchange(EXCHANGE_NAME, "topic", { durable: true });

    // Assert queues
    await channel.assertQueue(QUEUES.STATUS_UPDATE, { durable: true });
    await channel.assertQueue(QUEUES.NEW_REQUEST, { durable: true });

    // Bind queues to exchange
    await channel.bindQueue(
      QUEUES.STATUS_UPDATE,
      EXCHANGE_NAME,
      "status.update"
    );
    await channel.bindQueue(QUEUES.NEW_REQUEST, EXCHANGE_NAME, "request.new");

    console.log("RabbitMQ connected and queues/exchanges set up");
    return channel;
  } catch (error) {
    console.error("RabbitMQ connection error:", error);
    throw error;
  }
}

async function publishToQueue(queue, message) {
  if (!channel) {
    await connectRabbitMQ();
  }
  await channel.sendToQueue(queue, Buffer.from(JSON.stringify(message)), {
    persistent: true,
  });
}

async function publishToExchange(exchange, routingKey, message) {
  if (!channel) {
    await connectRabbitMQ();
  }
  await channel.publish(
    exchange,
    routingKey,
    Buffer.from(JSON.stringify(message)),
    {
      persistent: true,
    }
  );
}

module.exports = {
  connectRabbitMQ,
  getChannel: () => channel,
  publishToQueue,
  publishToExchange,
  QUEUES,
  EXCHANGE_NAME,
};
