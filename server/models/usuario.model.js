const { Schema, model } = require("mongoose");
const crypto = require("crypto");

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
    whatsapp: {
        type: String,
        trim: true,
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
        if (!password) return "";
        if (!this.salt) {
            console.error("Salt is not defined");
            return "";
        }
        try {
            const _password = crypto.pbkdf2Sync(
                password, this.salt, 1000, 64, `sha512`
            ).toString("hex");
            return _password;
        } catch (error) {
            console.error(error);
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
    .set(function(_psw = crypto.randomBytes(16).toString()){
        this.salt= this.makeSalt();
        this.contrasenia=this.encryptPassword(_psw);
    });

module.exports = model("Usuario", userShema);