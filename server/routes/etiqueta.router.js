const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");
const etiquetaController = require("../controllers/etiqueta.controller");

router.post("/save", runValidation, etiquetaController.create);
router.get("/findOne", runValidation, etiquetaController.findOne);
router.get("/findAll", runValidation, etiquetaController.findAll);
router.delete("/delete", runValidation, etiquetaController.deleteOne);

module.exports = router;