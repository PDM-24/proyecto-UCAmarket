const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const compraController = require("../controllers/compra.controller");
const { idInParams, createValidator, paginationValidator } = require("../validators/compra.validator");

router.post(["/", "/:id"], createValidator, runValidation, compraController.saveCompra);
router.get("/:id", idInParams, runValidation, compraController.findOne);
router.get("/user/:id", idInParams, paginationValidator, runValidation, compraController.findByUser);
router.delete("/:id", idInParams, runValidation, compraController.deleteOne);

module.exports = router;