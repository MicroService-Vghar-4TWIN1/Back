// server.js
const express = require("express");
const http = require("http");
const path = require("path");
const { Eureka } = require("eureka-js-client");
const mongoose = require("mongoose");
const configDb = require("./config/db.json");
const { connectRabbitMQ } = require("./config/rabbitmq");

const financialAidRouter = require("./routers/financialAid");

const app = express();
const server = http.createServer(app);

// Initialize RabbitMQ connection
connectRabbitMQ().catch((err) => {
  console.error("Failed to connect to RabbitMQ:", err);
});

// After RabbitMQ connection
const { startConsumers } = require("./services/financialAidConsumer");
connectRabbitMQ()
  .then(() => startConsumers())
  .catch((err) => console.error("Failed to start consumers:", err));

// Eureka client configuration remains the same...

app.set("views", path.join(__dirname, "views"));
app.set("view engine", "twig");

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use("/finance", financialAidRouter);

mongoose
  .connect(configDb.mongo.uri)
  .then(() => console.log("Connected to MongoDB"))
  .catch((error) => console.error("MongoDB connection error:", error));

server.listen(3000, () => {
  console.log("Server is running on http://localhost:3000");
});

// Graceful shutdown
process.on("SIGINT", async () => {
  console.log("Shutting down gracefully...");
  await mongoose.disconnect();
  if (connection) await connection.close();
  process.exit(0);
});
