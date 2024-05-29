const { body, param } = require("express-validator");

const validators = {};

validators.createValidator = [
    body("nombre")
        .trim()
        .notEmpty().withMessage("Picture is required")
        .isString().withMessage("Picture is a string")
];

validators.idInParams = [
    param("id")
        .notEmpty().withMessage("ID is required")
        .isMongoId().withMessage("ID is MongoID")
];

module.exports = validators;