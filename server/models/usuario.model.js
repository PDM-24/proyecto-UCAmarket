const Mongoose = require("mongoose");
const Schema = Mongoose.Schema;
const crypto = require("crypto");
const debug = require('debug')("app:user-model");

const userShema = new Schema({
    username:{
        type: String,
        trim: true,
        required: true,
        unique: true
    },
    correo:{
        type: String,
        trim: true,
        required: true,
        unique: true,
        lowercase: true
    },
    contrasenia:{
        type: String,
        trim: true,
        required: true
    },
    salt:{
        type: String
    },
    tokens:{
        type: [String],
        default: []
    },
    roles: {
        type: [String],
        default: []
    },
    profile_pic:{
        type: String,
        trim: true,
        default: ""
    },
    desc:{
        type: String,
        default: ""
    },
    wishlist:{
        type: [{
            user: {
                type: Schema.Types.ObjectId,
                ref: "Articulo",
                required: true
            },
            timestamps: {
                type: Date,
                required: true
            }
        }],
        default: []
    }
}, {timestamps: true});
userShema.methods = {
    encryptPassword: function(password) {
        if(!password) return "";
        try {
            const _password = crypto.pbkdf2Sync(
                password, this.salt, 1000, 64, `sha512`
            ).toString("hex");
            return _password
        } catch (error) {
            debug({ error });
            return "";        
        }
    },
    makeSalt: function() {
        return crypto.randomBytes(16).toString("hex");
    },
    comparePassword: function(password) {
        return this.contrasenia===this.encryptPassword(password);
    }
}
userShema
    .virtual("password")
    .set(function(password = crypto.randomBytes(16).toString()){
        this.salt= this.makeSalt();
        this.contrasenia=this.encryptPassword(password);
    });

module.exports = Mongoose.model("Usuario", userShema);