const express = require("express");
const http = require("http");
const path = require("path");
const { Eureka } = require("eureka-js-client");
const mongoose = require("mongoose");
const configDb = require("./config/db.json");
const { connectRabbitMQ } = require("./config/rabbitmq");

const financialAidRouter = require("./routers/financialAid");
const { startConsumer } = require("./services/financialAidConsumer");

const app = express();
const server = http.createServer(app);

// Initialize services
async function initializeServices() {
  try {
    // Connect to MongoDB
    await mongoose.connect(configDb.mongo.uri);
    console.log("Connected to MongoDB");

    // Connect to RabbitMQ and start consumer
    await connectRabbitMQ();
    console.log("RabbitMQ connected and queues/exchanges set up");

    await startConsumer();
    console.log("Financial Aid Consumer started successfully");
  } catch (error) {
    console.error("Initialization failed:", error);
    process.exit(1);
  }
}

// Express configuration
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "twig");
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use("/finance", financialAidRouter);

// Start server
server.listen(3000, () => {
  console.log("Server is running on http://localhost:3000");
});

// Initialize all services
initializeServices();

// Graceful shutdown
let rabbitMQConnection; // You'll need to export this from rabbitmq.js

process.on("SIGINT", async () => {
  console.log("Shutting down gracefully...");
  try {
    await mongoose.disconnect();
    if (rabbitMQConnection) await rabbitMQConnection.close();
    console.log("All connections closed");
    process.exit(0);
  } catch (error) {
    console.error("Shutdown error:", error);
    process.exit(1);
  }
});
