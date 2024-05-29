const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const ROLES = require("../data/roles.constants.json");
const { authentication } = require("../validators/auth.middleware");
const { registerValidator, updateUserValidator, dataValidator ,idInParams, paginationValidator } = require("../validators/auth.validator");
const authController = require("../controllers/auth.controller");

router.post("/register", registerValidator, runValidation, authController.register);
router.post("/login", authController.login);
router.get("/aboutMe", authentication, runValidation,authController.aboutMe);
router.get("/:id", idInParams, runValidation, authController.findOneUser);
router.get("/", paginationValidator, runValidation, authController.findAll);
router.patch("/", authentication, updateUserValidator, runValidation, authController.updateUser);
router.patch("/password/", authentication, updateUserValidator, runValidation, authController.changePassword);
router.patch("/reputation/:id", authentication, idInParams, updateUserValidator, runValidation, authController.changeReputation);

module.exports = router;