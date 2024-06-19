const express = require("express");
const router = express.Router();

const authRouter = require("./auth.router")
const emprendimientoRouter = require("./emprendimiento.router")
const productRouter = require("./articulo.router")
// const serviceRouter = require("./servicio.router")
const etiquetaRouter = require("./etiqueta.router")

router.use("/auth", authRouter);
router.use("/emprendimiento", emprendimientoRouter);
router.use("/product", productRouter);
// router.use("/service",serviceRouter);
router.use("/etiqueta", etiquetaRouter);
module.exports = router;