const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const compraShema = new Schema({
    producto: {
        type: Schema.Types.ObjectId,
        ref: "Articulo",
        required:true
    },
    comprador: {
        type: Schema.Types.ObjectId,
        ref: "Usuario",
        required:true
    },
    opinion: {
        type: {
            calificacion:{
                type: String,
                required: true,
                trim: true
            },
            comentario:{
                type: String,
                required: true,
                trim: true
            }
        },
        required: true
    }
}, {timestamps: true});

module.exports = Mongoose.model("Compra", compraShema);