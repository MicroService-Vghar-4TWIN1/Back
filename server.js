const express = require("express");
const http = require("http");
const path = require("path");
const { Eureka } = require('eureka-js-client');

const mongoose = require("mongoose");
const configDb = require("./config/db.json");

const financialAidRouter = require('./routers/financialAid');

const eurekaClient = new Eureka({
    instance: {
      app: 'FINANCIALAID', 
      instanceId: 'financial-aid-service-1',
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

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'twig');
 
app.use(express.json());
app.use(express.urlencoded({extended: false}));

app.use('/finance', financialAidRouter);
// ceci est un exemple :


mongoose
  .connect(configDb.mongo.uri)
  .then(() => {
    console.log("Connected to MongoDB");
  })
  .catch((error) => {
    console.error("MongoDB connection error:", error);
  });


server.listen(3000, ()=>{
    console.log('server is running on http://localhost:3000');
});
