const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const emprendimientoShema = new Schema({
    nombre:{
        type: String,
        required: true,
        trim: true
    },
    descripcion: {
        type: String,
        trim: true,
        required: true
    },
    contacto: {
        type: String
    }
}, {timestamps: true});

module.exports = Mongoose.model("Emprendimiento", emprendimientoShema);