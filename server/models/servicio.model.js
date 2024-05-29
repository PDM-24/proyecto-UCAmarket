const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const servicioShema = new Schema({
    nombre:{
        type: String,
        required: true,
        trim: true
    },
    tarifa:{
        type: float,
        trim: true,
        required: true
    },
    descripcion: {
        type: String,
        trim: true,
        required: true
    },
    contacto: {
        type: String,
        trim: true,
        required: true
    }
}, {timestamps: true});

module.exports = Mongoose.model("Servicio", servicioShema);