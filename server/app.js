const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const database = require("./config/database.config");
const apiRouter = require("./routes/index.router")

const app = express();
database.connect();

//logger for request
app.use(logger('dev'));

//body parsers
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

//static router
app.use(express.static(path.join(__dirname, 'public')));

// API Router
app.use("/api/v1", apiRouter);

module.exports = app;
