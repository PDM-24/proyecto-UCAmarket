const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const etiquetaController = require("../controllers/etiqueta.controller");
const { idInParams, createValidator } = require("../validators/etiqueta.validator");

router.post("/", createValidator, runValidation, etiquetaController.create);
router.get("/:id", idInParams, runValidation, etiquetaController.findOne);
router.get("/", runValidation, etiquetaController.findAll);
router.delete("/:id", idInParams, runValidation, etiquetaController.deleteOne);

module.exports = router;