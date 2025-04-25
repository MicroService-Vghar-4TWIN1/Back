const express = require("express");
const http = require("http");
const path = require("path");
const { requestDepartments } = require('./rabbit/rpcClient');

const { jwtCheck } = require('./middlewares/roles');
const { Eureka } = require('eureka-js-client');
require("dotenv").config();
const mongoose = require("mongoose");
const cors = require('cors');

// Initialize express app first
const app = express();
const server = http.createServer(app);

console.log("MongoDB URI:", process.env.MONGODB_URI); // Debugging

const financialAidRouter = require('./routers/financialAid');

// CORS Configuration - now app is defined

// Eureka Client Configuration
const eurekaClient = new Eureka({
  instance: {
    app: 'FINANCIALAID',
    instanceId: 'finance-service-1',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    port: {
      '$': 3000,
      '@enabled': true,
    },
    vipAddress: 'FINANCIALAID',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: 'localhost',
    port: 8761,
    servicePath: '/eureka/apps/',
    maxRetries: 10,
    requestRetryDelay: 2000,
  },
});

eurekaClient.start((error) => {
  if (error) {
    console.error('Eureka registration failed:', error);
  } else {
    console.log('Service registered with Eureka!');
  }
});

// View engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'twig');

// Middleware
app.use(express.json());
app.use(express.urlencoded({extended: false}));

// Routes
app.use('/finance', jwtCheck, financialAidRouter);

// MongoDB connection
mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/financeDB')
  .then(() => {
    console.log("Connected to MongoDB");
  })
  .catch((error) => {
    console.error("MongoDB connection error:", error);
  });

// Start server
server.listen(3000, '0.0.0.0', () => {
  console.log('Server is running on http://localhost:3000');
});