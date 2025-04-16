const express = require("express");
const http = require("http");
const path = require("path");

const mongoose = require("mongoose");
const configDb = require("./config/db.json");

const financialAidRouter = require('./routers/financialAid');
// ceci est un exemple :



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
