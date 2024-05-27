const Mongoose = require("mongoose");
const debug = require(`debug`)("app:db");

const dbhost = process.env.DBHOST || "localhost";
const dbport = process.env.DBPORT || "27017";
const dbname = process.env.DBNAME || "viajesSV";

const dburl = process.env.DBURL || `mongodb://${dbhost}:${dbport}/${dbname}`;

const connect = async () => {
    try {
        await Mongoose.connect(dburl);
        debug("Connection to database started");
    } catch (e) {
        console.error(e);
        debug("Cannot connect to database");
        process.exit(1);
    }
}

const disconnect = async () => {
    try {
        await Mongoose.disconnect();
    } catch (e) {
        process.exit(1);
    }
}

module.exports = {
    connect,
    disconnect
}