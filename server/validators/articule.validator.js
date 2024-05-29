const { body, param, query } = require("express-validator");

const validators = {};

validators.createArticuleValidator = [
    param("id")
        .optional()
        .isMongoId().withMessage("ID is MongoID"),
    body("nombre")
        .trim()
        .notEmpty().withMessage("User is required")
        .isLength( {min:4 , max:32 }).withMessage("Username format incorrect"),
    body("descripcion")
        .trim()
        .notEmpty().withMessage("Description is required")
        .isLength( {min:4 , max:32 }).withMessage("Description format incorrect"),
    body("lista_deseos")
        .trim()
        .notEmpty().withMessage("Lista_deseos is required")
        .isString().withMessage("Lista_deseos format incorrect"),
    body("precio")
        .optional()
        .trim()
        .isNumeric().withMessage("Precio format incorrect"),
    body("etiqueta")
        .optional()
        .trim()
        .notEmpty().withMessage("Etiqueta is required")
        .isArray().withMessage("Etiqueta is array"),
    body("etiqueta.*")
        .optional()
        .trim()
        .isMongoId().withMessage("Etiqueta is MongoID")
];

validators.etiquetaValidator = [
    body("etiqueta")
        .trim()
        .notEmpty().withMessage("Etiqueta is required")
        .isArray().withMessage("Etiqueta is array"),
    body("etiqueta.*")
        .isMongoId().withMessage("Etiqueta is MongoID")
        .trim()
]

validators.disponibilidadValidator = [
    body("estado")
        .notEmpty().withMessage("Status is required")
        .isIn(["Disponible", "Agotado", "Reservado"]).withMessage("Status value incorrect")
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