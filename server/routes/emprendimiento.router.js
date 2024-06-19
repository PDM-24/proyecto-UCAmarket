const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const { authentication } = require("../validators/emprendimiento.middleware");
const { registerValidator, updateUserValidator } = require("../validators/emprendimiento.validator");
const { idInParams, paginationValidator } = require("../validators/utils.validator")
const authController = require("../controllers/emprendimiento.controller");

router.post("/register", registerValidator, runValidation, authController.register);
router.post("/login", authController.login);
router.get("/aboutMe", authentication, runValidation,authController.aboutMe);
router.get("/:id", idInParams, runValidation, authController.findOneUser);
router.get("/", paginationValidator, runValidation, authController.findAll);
router.patch("/", authentication, updateUserValidator, runValidation, authController.updateUser);
router.patch("/password/", authentication, updateUserValidator, runValidation, authController.changePassword);

module.exports = router;