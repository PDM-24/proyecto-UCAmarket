const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const imgController = require("../controllers/imgArt.controller");
const { ImgArtValidator, idInParams, ArticuleValidator, paginationValidator } = require("../validators/imgArt.validator");

router.post(["/", "/:id"], ImgArtValidator, runValidation, imgController.saveImg);
router.get("/art/:id", idInParams, paginationValidator, runValidation, imgController.findByArt);
router.delete("/:id", idInParams, ArticuleValidator, imgController.deleteOneImagen);
router.delete("/art/:id", idInParams, runValidation, imgController.deleteAllbyArt);

module.exports = router;