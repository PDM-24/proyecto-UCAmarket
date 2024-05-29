const { param, body, query } = require("express-validator");

const validators = {};

validators.createValidator = [
    param("id")
        .optional()
        .trim()
        .isMongoId().withMessage("ID is MongoID"),
    body("user")
        .trim()
        .notEmpty().withMessage("User is required")
        .isMongoId().withMessage("User is MongoID"),
    body("articule")
        .trim()
        .notEmpty().withMessage("Articule is required")
        .isMongoId().withMessage("Articule is MongoID"),
    body("opinion.*.calificacion")
        .trim()
        .notEmpty().withMessage("Calificacion is required")
        .isNumeric().withMessage("Calificacion is a number")
        .isInt({min: 0, max: 5}).withMessage("Callificacion is between 0 and 5"),
    body("opinion.*.comentario")
        .trim()
        .notEmpty().withMessage("Calificacion is required")
        .isString().withMessage("Calificacion is a string")
];

validators.idInParams = [
    param("id")
        .notEmpty().withMessage("ID is required")
        .isMongoId().withMessage("ID is MongoID")
];

validators.paginationValidator = [
    query("paginator")
        .notEmpty().withMessage("Pagination is required")
        .isBoolean().withMessage("Pagination is boolean"),
    query("limit")
        .optional()
        .isNumeric().withMessage("Limit is a number"),
    query("offset")
        .optional()
        .isNumeric().withMessage("Offset is a number")
];

module.exports = validators;