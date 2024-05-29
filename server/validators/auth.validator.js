const { body, param, query } = require("express-validator");

const validators = {};
const passwordRegexp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,32})/

validators.registerValidator = [
    body("username")
        .trim()
        .notEmpty().withMessage("User is required")
        .isLength( {min:4 , max:32 }).withMessage("Username format incorrect"),
    body("email")
        .trim()
        .notEmpty().withMessage("Email is required")
        .isEmail().withMessage("Email format incorrect"),
    body("password")
        .trim()
        .notEmpty().withMessage("Password is required")
        .matches(passwordRegexp).withMessage("Password format incorrect")
];

validators.updateUserValidator = [
    body("username")
        .optional()
        .trim()
        .notEmpty().withMessage("Username is not null")
        .isLength({ max:32 }).withMessage("Username format incorrect"),
    body("password")
        .optional()
        .trim()
        .matches(passwordRegexp).withMessage("Password format incorrect"),
    body("picture")
        .optional()
        .trim()
        .isString().withMessage("Picture is a string")
        .isURL().withMessage("Picture is a URL"),
    body("desc")
        .optional()
        .trim()
        .isString().withMessage("Desc is a String")
        .isLength( { max:150 }).withMessage("Contact format incorrect"),
    body("addReputacion")
        .optional()
        .isBoolean().withMessage("Reputacion is boolean")
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