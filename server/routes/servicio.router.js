const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const ROLES = require("../data/roles.constants.json");
const { authentication, authorization } = require("../validators/auth.middleware");
const servicioController = require("../controllers/servicio.controller");

router.post("/save", authentication, authorization(ROLES.EMPRENDE), runValidation, servicioController.save);
router.get("/findOne", runValidation, servicioController.findOne);
router.get("/findAll", runValidation, servicioController.findAll);
router.get("/byEmprendimiento", runValidation, servicioController.findByEmprendimiento);
router.get("/own", authentication, authorization(ROLES.EMPRENDE), runValidation, servicioController.findOwn);
router.delete("/delete", authentication, authorization(ROLES.EMPRENDE), runValidation, servicioController.deleteOne);

module.exports = router;