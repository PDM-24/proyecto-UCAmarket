const Etiqueta = require("../models/articulo.model");
const controller = {};

controller.create = async(req, res, next)=>{
    try {
        const { nombre }= req.body;
        const etiqueta = await Etiqueta.find({ nombre: nombre });
        if(etiqueta){
            return res.status(409).json({ error: "Etiqueta already exists!"})
        }
        const newEtiqueta = new Etiqueta({
            nombre: nombre
        });
        await newEtiqueta.save();
        return res.status(201).json({ message: "Etiqueta registered" });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

controller.findAll= async(req, res, next)=>{
    try {
        const etiquetas = await Etiqueta.find()
        return res.status(200).json({ etiquetas });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.findOne= async(req, res, next)=>{
    try {
        const {id}= req.params;
        const etiqueta = await Etiqueta.findById(id);
        if(!etiqueta){
            return res.status(404).json({ error: "Etiqueta not found"});
        }
        return res.status(200).json({ etiqueta });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.deleteOne = async(req, res, next)=>{
    try {
        const {id} = req.params;
        const deletedEtiqueta = await Etiqueta.findByIdAndDelete(id);
        if(!deletedEtiqueta){
            return res.status(404).json({ error: "Etiqueta not found"});
        }
        return res.status(200).json({ message: "Etiqueta deleted" });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

module.exports = controller;