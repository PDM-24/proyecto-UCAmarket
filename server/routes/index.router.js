const express = require("express");
const router = express.Router();

const authRouter = require("./auth.router");
const productRouter = require("./articule.router");
const serviceRouter = require("./servicio.router");
const etiquetaRouter = require("./etiqueta.router");

router.use("/auth", authRouter);
router.use("/product", productRouter);
router.use("/service", serviceRouter);
router.use("/etiqueta", etiquetaRouter);

module.exports = router;