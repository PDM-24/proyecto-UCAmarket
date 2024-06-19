const debug = require('debug')("app:auth-middleware");
const Emprendimiento = require("../models/emprendimiento.model");
const { verifyToken } = require("../utils/jwt.tools");
const PREFIX= "Bearer";

const middleware = {};
middleware.authentication = async(req, res, next)=>{
    try {
        const { authorization } = req.headers;
        if(!authorization){
            return res.status(401).json({error: "emprendimiento not authenticated"});
        }
        const [prefix, token ] = authorization.split(" ");
        if(prefix !== PREFIX){
            return res.status(401).json({error: "emprendimiento not authenticated"});
        }
        if(!token){
            return res.status(401).json({error: "emprendimiento not authenticated"});
        }
        const payload = await verifyToken(token);
        if(!payload){
            return res.status(401).json({error: "emprendimiento not authenticated"});
        }
        const emprendimientoId = payload["sub"]
        const emprendimiento = await Emprendimiento.findById(emprendimientoId);
        if(!emprendimiento){
            return res.status(401).json({error: "emprendimiento not authenticated"});
        }
        const isTokenValid = emprendimiento.tokens.includes(token);
        if(!isTokenValid){
            return res.status(401).json({error: "emprendimiento not authenticated"});
        }
        req.emprendimiento = emprendimiento;
        req.token = token;
        next();
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

module.exports =  middleware;