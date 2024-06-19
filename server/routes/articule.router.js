const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const ROLES = require("../data/roles.constants.json");
const { authentication } = require("../validators/emprendimiento.middleware");
const { createArticuleValidator, disponibilidadValidator} = require("../validators/articule.validator");
const { idInParams, paginationValidator } = require("../validators/utils.validator")
const articuleController = require("../controllers/articulo.controller");

router.post(["/", "/:id"], authentication, createArticuleValidator, runValidation, articuleController.saveArt);
router.get("/", runValidation, articuleController.findAll);
router.get("/:id", idInParams, runValidation, articuleController.findOneById);
router.get("/etiqueta/:id", idInParams, paginationValidator, runValidation, articuleController.findByEtiqueta);
router.get("/emprendimiento/:id", idInParams, paginationValidator, runValidation, articuleController.findByEmprendimiento);
router.get("/own", authentication, paginationValidator, runValidation, articuleController.findOwn);
router.patch("/hidden/:id", authentication, idInParams, runValidation, articuleController.changeHidden);
router.patch("/status/:id", authentication, idInParams, disponibilidadValidator , runValidation, articuleController.changeDisponibilidad);
router.delete("/:id", authentication, idInParams, runValidation, articuleController.deleteOneArticle);

module.exports = router;