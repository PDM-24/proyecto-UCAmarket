const Comprar = require("../models/compra.model");

const controller = {};

controller.saveCompra = async(req, res, next)=>{
    try {
        const { user, articule, opinion } =req.body;
        const { id } = req.params;
        let venta = await Comprar.findById(id);
        if(!venta){
            venta = new Comprar();
        };
        venta["producto"] = articule;
        venta["comprador"] = user;
        venta["opinion"] = opinion;
        const savedVenta = await venta.save();
        if(!savedVenta){
            return res.status(409).json({error: "Error creating venta"});
        }
        return res.status(201).json( savedVenta );
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.findOne= async(req, res, next)=>{
    try {
        const { id }= req.params
        const compra = await Comprar.findById(id);
        if(!compra){
            return res.status(404).json({ error: "Compra not found" })
        }
        return res.status(200).json({ compra });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.findByUser= async(req, res, next)=>{
    try {
        const { user }= req.params;
        const { pagination=true, limit=5, offset=0 }= req.query;
        const compras = await Comprar.find({ comprador: user}, undefined, {
            sort: [{ createdAt: -1}],
            limit: pagination?limit:undefined,
            skip: pagination?offset:undefined 
        });
        return res.status(200).json({ compras ,
            count: pagination ? await Comprar.countDocuments({hidden: false}): undefined
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
};

controller.deleteOne = async(req, res, next)=>{
    try {
        const {id} = req.params;
        const deletedCompra = await Comprar.findByIdAndDelete(id);
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