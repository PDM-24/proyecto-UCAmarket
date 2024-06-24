const { Schema, model } = require("mongoose");

const etiquetaShema = new Schema({
    nombre:{
        type: String,
        unique: true,
        required: true,
        trim: true,
        lowercase: true
    }
}, {timestamps: true});

module.exports = model("Etiqueta", etiquetaShema);