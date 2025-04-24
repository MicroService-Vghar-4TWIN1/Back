const express = require("express");
const http = require("http");
const path = require("path");
const cors = require("cors"); // Add this line

const { Eureka } = require('eureka-js-client');
require("dotenv").config();

const mongoose = require("mongoose");
console.log("MongoDB URI:", process.env.MONGODB_URI); // Debugging

const financialAidRouter = require('./routers/financialAid');
// CORS Configuration
const corsOptions = {
  origin: 'http://localhost:4200',
  methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
  allowedHeaders: ['Content-Type', 'Authorization'],
  credentials: true
};

const eurekaClient = new Eureka({
  instance: {
    app: 'FINANCIALAID',
    instanceId: 'finance-service-1',
    hostName: 'financeService',
    ipAddr: 'financeService',
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
    host: 'eureka-server',
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


const app = express();
const server = http.createServer(app);
app.use(cors(corsOptions)); // Apply CORS middleware

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'twig');
 
app.use(express.json());
app.use(express.urlencoded({extended: false}));

app.use('/finance', financialAidRouter);
// ceci est un exemple :


mongoose.connect(process.env.MONGODB_URI|| 'mongodb://localhost:27017/financeDB')
  .then(() => {
    console.log("Connected to MongoDB");
  })
  .catch((error) => {
    console.error("MongoDB connection error:", error);
  });


server.listen(3000, '0.0.0.0', ()=>{
    console.log('server is running on http://localhost:3000');
});
