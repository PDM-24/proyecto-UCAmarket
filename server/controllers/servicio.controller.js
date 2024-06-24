const Servicio = require("../models/servicio.model");
const controller = {};

controller.save = async(req, res, next)=>{
    try {
        const { nombre, descripcion } =req.body;
        const { id } = req.query;
        const { emprendimiento } = req;
        let servicio = await Servicio.findById(id);
        if(!servicio){
            servicio = new Servicio();
            servicio["contacto"] = emprendimiento._id;
        }else{
            if(!servicio["contacto"].equals(emprendimiento._id)){
                return res.status(403).json({ error: "This is not your servicio"});
            }
        };
        servicio["nombre"] = nombre;
        servicio["desc"] = descripcion;
        const savedServicio = (await servicio.save())
            .populate("contacto", "nombre profile_pic whatsapp");
        if(!savedServicio){
            return res.status(409).json({error: "Error creating servicio"});
        }
        return res.status(201).json( savedServicio );
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

controller.findAll= async(req, res, next)=>{
    try {
        const servicios = await Servicio.find()
            .populate("contacto", "nombre profile_pic whatsapp")
        return res.status(200).json({ servicios });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.findOne= async(req, res, next)=>{
    try {
        const {id}= req.params;
        const servicio = await Servicio.findById(id)
            .populate("contacto", "nombre profile_pic whatsapp");
        if(!servicio){
            return res.status(404).json({ error: "Servicio not found"});
        }
        return res.status(200).json({ servicio });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.findByEmprendimiento = async (req, res, next)=>{
    try {
        const {id}= req.query;
        const articules = await Servicio.find({ contacto: id, hidden: false })
            .populate("contacto", "nombre profile_pic whatsapp");
        return res.status(200).json({ articules });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.findOwn = async (req, res, next)=>{
    try {
        const { _id: emprendimientoId}= req.emprendimiento;
        const articules = await Servicio.find({ contacto: emprendimientoId });
        return res.status(200).json({ articules });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.deleteOne = async(req, res, next)=>{
    try {
        const {id}= req.query;
        const { user } = req;
        const deletedService = await Servicio.findOneAndDelete({ _id: id, contacto: user._id });
        if(!deletedService){
            return res.status(404).json({ error: "Service not found" });
        }
        return res.status(200).json({ message: "Service deleted" });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

module.exports = controller;