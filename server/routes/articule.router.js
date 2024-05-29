const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const ROLES = require("../data/roles.constants.json");
const { authentication, authorization } = require("../validators/auth.middleware");
const { createArticuleValidator, disponibilidadValidator, idInParams, paginationValidator } = require("../validators/articule.validator");
const articuleController = require("../controllers/articulo.controller");

router.post(["/", "/:id"], authentication, authorization(ROLES.USER), createArticuleValidator, runValidation, articuleController.saveArt);
router.get("/", runValidation, articuleController.findAll);
router.get("/:id", idInParams, runValidation, articuleController.findOneById);
router.get("/etiqueta/:id", idInParams, paginationValidator, runValidation, articuleController.findByEtiqueta);
router.get("/user/:id", idInParams, paginationValidator, runValidation, articuleController.findByUser);
router.get("/offer", authentication, paginationValidator, runValidation, articuleController.findMyOffers);
router.get("/own", authentication, authorization(ROLES.USER), paginationValidator, runValidation, articuleController.findOwn);
router.patch("/hidden/:id", authentication, authorization(ROLES.USER), idInParams, runValidation, articuleController.changeHidden);
router.patch("/status/:id", authentication, authorization(ROLES.USER), idInParams, disponibilidadValidator , runValidation, articuleController.changeDisponibilidad);
router.patch("/offer/:id", authentication, idInParams, runValidation, articuleController.offerArticule)
router.delete("/:id", authentication, authorization(ROLES.USER), idInParams, runValidation, articuleController.deleteOneArticle);

module.exports = router;