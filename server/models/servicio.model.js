const { Schema, model } = require("mongoose");

const servicioShema = new Schema({
    nombre:{
        type: String,
        required: true,
        trim: true
    },
    desc: {
        type: String,
        trim: true,
        required: true
    },
    contacto: {
        type: Schema.Types.ObjectId,
        ref: "Usuario",
        required:true
    }
}, {timestamps: true});

module.exports = model("Servicio", servicioShema);