// rabbit/rpcClient.js
const amqp = require('amqplib');
const { v4: uuidv4 } = require('uuid');

async function requestDepartments() {
  const connection = await amqp.connect('amqp://localhost');
  const channel = await connection.createChannel();

  const requestQueue = 'department.financialAid.request';
  const correlationId = uuidv4();
  const replyQueue = await channel.assertQueue('', { exclusive: true });

  return new Promise((resolve) => {
    channel.consume(replyQueue.queue, (msg) => {
      if (msg.properties.correlationId === correlationId) {
        const response = JSON.parse(msg.content.toString());
        resolve(response);
        setTimeout(() => connection.close(), 500);
      }
    }, { noAck: true });

    channel.sendToQueue(requestQueue, Buffer.from('Which departments need aid?'), {
      correlationId,
      replyTo: replyQueue.queue,
    });
  });
}

module.exports = { requestDepartments };
