require('dotenv').config();

const express = require('express');
const mongoose = require('mongoose');
const routes = require('./routes/index.router')

const dbhost = process.env.DBHOST || 'localhost';
const dbport = process.env.DBPORT || '27017';
const dbname = process.env.DBNAME || 'ucamarket';
const dburl = process.env.DBURL || `mongodb://${dbhost}:${dbport}/${dbname}`;

mongoose.connect(dburl);
const database =  mongoose.connection;
database.on( 'error', (error)=>{
    console.log(error);
})
database.once('connected', ()=>{
    console.log("Database connected!");
})

const app = express();
app.use(express.json());
app.use('/api', routes);

const port = process.env.PORT || 3000;
app.listen(port, ()=>{
    console.log(`Server started at port ${port}`)
})