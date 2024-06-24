const { body } = require("express-validator");
const validators = {};

validators.emailValidator=[
    body("origen")
        .trim()
        .notEmpty().withMessage("Origen is required")
        .isEmail().withMessage("Origen is a email"),
    body("destinatario")
        .trim()
        .notEmpty().withMessage("Destinatario is required")
        .isEmail().withMessage("Destinatario is a email"),
    body("asunto")
        .trim()
        .notEmpty().withMessage("Asunto is required")
        .isString().withMessage("Asunto is string")
        .isLength({ max: 25}).withMessage("Asunto max-length is 25"),
    body("contenido")
        .trim()
        .notEmpty().withMessage("Contenido is required")
        .isString().withMessage("Contenido is string")
];

module.exports = validators;