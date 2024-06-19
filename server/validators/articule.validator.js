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
    body("picture")
        .optional()
        .trim()
        .isString().withMessage("picture format incorrect"),
    body("precio")
        .trim()
        .notEmpty().withMessage("Precio is required")
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

module.exports = validators;