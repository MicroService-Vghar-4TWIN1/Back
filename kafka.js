const { Kafka, Partitioners } = require("kafkajs"); 
const kafka = new Kafka({
  clientId: "financial-aid-service",
  brokers: ["localhost:9092"], 
  createPartitioner: Partitioners.LegacyPartitioner, 
});

const producer = kafka.producer();

const connectProducer = async () => {
  await producer.connect();
  console.log("Kafka Producer Connected");
};

module.exports = { producer, connectProducer };
