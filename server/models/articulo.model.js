const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const articuloShema = new Schema({
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
    lista_deseos: {
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
    user: {
        type: Schema.Types.ObjectId,
        ref: "Usuario",
        required:true
    },
    ofertas:{
        type: [Schema.Types.ObjectId],
        ref: "Usuario",
        default: []
    }
}, {timestamps: true});

module.exports = Mongoose.model("Articulo", articuloShema);