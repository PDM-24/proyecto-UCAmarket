const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;

const imgArtShema = new Schema({
    reference: {
        type: Schema.Types.ObjectId,
        ref: "Articulo",
        trim: true,
        required: true
    },
    dir:{
        type: String,
        required: true,
        trim: true
    }
}, {timestamps: true});

module.exports = Mongoose.model("imgArticulos", imgArtShema);