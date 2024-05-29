const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const etiquetaShema = new Schema({
    nombre:{
        type: String,
        unique: true,
        required: true,
        trim: true,
        lowercase: true
    }
}, {timestamps: true});

module.exports = Mongoose.model("Etiqueta", etiquetaShema);