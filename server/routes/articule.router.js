const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const ROLES = require("../data/roles.constants.json");
const { authentication, authorization } = require("../validators/auth.middleware");
const articuleController = require("../controllers/articulo.controller");

router.post("/save", authentication, authorization(ROLES.EMPRENDE), runValidation, articuleController.saveArt);
router.get("/findAll", runValidation, articuleController.findAll);
router.get("/findOne", runValidation, articuleController.findOneById);
router.get("/byEtiqueta", runValidation, articuleController.findByEtiqueta);
router.get("/byEmprendimiento", runValidation, articuleController.findByEmprendimiento);
router.get("/own", authentication, runValidation, articuleController.findOwn);
router.patch("/hidden", authentication, authorization(ROLES.EMPRENDE), articuleController.changeHidden);
router.patch("/status", authentication, authorization(ROLES.EMPRENDE), runValidation, articuleController.changeDisponibilidad);
router.delete("/delete", authentication, authorization(ROLES.EMPRENDE), runValidation, articuleController.deleteOneArticle);

module.exports = router;