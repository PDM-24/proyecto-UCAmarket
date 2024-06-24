const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const ROLES = require("../data/roles.constants.json");
const { authentication, authorization } = require("../validators/auth.middleware");
const authController = require("../controllers/auth.controller");

router.post("/register", runValidation, authController.register);
router.post("/login", authController.login);
router.get("/aboutMe", authentication, runValidation,authController.aboutMe);
router.get("/findOne", runValidation, authController.findOneUser);
router.get("/findAll", runValidation, authController.findAllEmprendimientos);
router.patch("/save", authentication, runValidation, authController.updateUser);
router.patch("/password", authentication, runValidation, authController.changePassword);
router.patch("/wishlist", authentication, authorization(ROLES.USER), runValidation, authController.editWishlist);

module.exports = router;