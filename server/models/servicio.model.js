const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const servicioShema = new Schema({
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
        type: Schema.Types.ObjectId,
        ref: "Emprendimiento",
        required:true
    }
}, {timestamps: true});

module.exports = Mongoose.model("Servicio", servicioShema);