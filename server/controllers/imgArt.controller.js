const Imagen = require("../models/imagenes.model");

const controller = {};

controller.saveImg = async(req, res, next)=>{
    try {
        const { Art, Img } = req.body;
        const { id } = req.params;
        let imagen = await Imagen.findOne({ $or: [{_id: id},{reference: Art, dir: Img}] });
        if(!imagen){
            imagen = new Articulo();
        };
        imagen["reference"] = Art;
        imagen["dir"] = Img;
        const savedImg = await imagen.save();
        if(!savedImg){
            return res.status(409).json({error: "Error creating imagens"});
        }
        return res.status(201).json( savedImg );
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

controller.findByArt = async(req, res, next)=>{
    try {
        const {id}= req.params;
        const { pagination = true, limit=5, offset=0 }= req.query;
        const imgArt = await Imagen.find({ reference: id }, undefined, {
            sort: [{ createdAt: -1}],
            limit: pagination?limit:undefined,
            skip: pagination?offset:undefined 
        }).populate("Articulo", "nombre descripcion estado");
        return res.status(200).json({ imgArt ,
            count: pagination ? await Articulo.countDocuments({hidden: false}): undefined
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

controller.deleteOneImagen = async(req, res, next)=>{
    try {
        const { id }= req.params;
        const { art } = req.body;
        const deletedImagen = await Articulo.findOneAndDelete({ _id: id, reference: art });
        if(!deletedImagen){
            return res.status(404).json({ error: "Imagen not found" });
        }
        return res.status(200).json({ message: "Imagen deleted" });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

controller.deleteAllbyArt = async(req, res, next)=>{
    try {
        const {id}= req.params;
        const deletedImgs = await Imagen.findAndDelete({ reference: id });
        if(!deletedImgs){
            return res.status(404).json({ error: "Imagens not found" });
        }
        return res.status(200).json({ imgArt });
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
}

module.exports = controller;
