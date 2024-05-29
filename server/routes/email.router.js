const express = require("express");
const router = express.Router();
const runValidation = require("../validators/index.middleware");

const { emailValidator } = require("../validators/email.validator");
const emailController = require("../controllers/email.controller");

router.get("/send", emailValidator, runValidation, emailController.sendMail);

module.exports = router;