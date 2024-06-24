const { Schema, model } = require("mongoose");

const articuloShema = new Schema({
    nombre:{
        type: String,
        required: true,
        trim: true
    },
    picture: {
        type: String,
        required: true,
        trim: true
    },
    desc: {
        type: String,
        trim: true,
        required: true
    },
    precio:{
        type: Number,
        min: 0.00,
        default: 0.00
    },
    etiqueta: {
        type: [Schema.Types.ObjectId],
        ref: "Etiqueta",
        trim: true,
        default: []
    },
    estado:{
        type: String,
        default: "Disponible",
        trim: true
    },
    hidden: {
        type: Boolean,
        default: false
    },
    emprendimiento: {
        type: Schema.Types.ObjectId,
        ref: "Usuario",
        required:true
    }
}, {timestamps: true});

module.exports = model("Articulo", articuloShema);