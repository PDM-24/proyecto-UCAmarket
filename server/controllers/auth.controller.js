const { createToken, verifyToken } = require("../utils/jwt.tools");
const User = require("../models/usuario.model");
const ROLES = require("../data/roles.constants.json");

const controller = {};

controller.register = async (req, res, next) => {
  try {
    const { username, email, password } = req.body;
    const user = await User.findOne({
      $or: [{ username: username }, { correo: email }],
    });
    if (user) {
      return res.status(409).json({ error: "User already exists!" });
    }
    const newUser = new User({
      username: username,
      correo: email,
      contrasenia: password,
      roles: [ROLES.USER],
    });
    await newUser.save();
    return res.status(201).json({ message: "User registered" });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.login = async (req, res, next) => {
  try {
    const { email, password } = req.body;
    const user = await User.findOne({ correo: email });
    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }
    if (!user.comparePassword(password)) {
      return res.status(401).json({ error: "Incorrect Password" });
    }
    const token = await createToken(user._id);
    let _tokens = [...user.tokens];
    const _verifyPromises = _tokens.map(async (_t) => {
      const status = await verifyToken(_t);
      return status ? _t : null;
    });
    _tokens = (await Promise.all(_verifyPromises))
      .filter((_t) => _t)
      .slice(0, 4);
    _tokens = [token, ..._tokens];
    user.tokens = _tokens;
    await user.save();
    return res.status(200).json({ token });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.aboutMe = async (req, res, next) => {
  try {
    const { _id, username, correo, roles, profile_pic, reputacion } = req.user;
    return res
      .status(200)
      .json({ _id, username, correo, roles, profile_pic, reputacion });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.findOneUser = async (req, res, next) => {
  try {
    const { id } = req.params;
    const user = await User.findById(id);
    // .populate("reputacion.usuario", "username correo")
    // .populate("reputacion", "recomendacion timestamps");
    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }
    return res.status(200).json({ user });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.findAll = async (req, res, next) => {
  try {
    const { pagination = true, limit = 5, offset = 0 } = req.query;
    const articules = await User.find({ hidden: false }, undefined, {
      sort: [{ createdAt: -1 }],
      limit: pagination ? limit : undefined,
      skip: pagination ? offset : undefined,
    });
    // .populate("reputacion.usuario", "username correo")
    // .populate("reputacion", "recomendacion timestamps");
    return res
      .status(200)
      .json({
        articules,
        count: pagination
          ? await User.countDocuments({ hidden: false })
          : undefined,
      });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.updateUser = async (req, res, next) => {
  try {
    const { _id } = req.user;
    const { username, picture, desc } = req.body;
    const updatedUser = await User.findByIdAndUpdate(
      _id,
      {
        username: username,
        profile_pic: picture,
        desc: desc,
      },
      { new: true }
    );
    // .populate("reputacion.usuario", "username correo")
    // .populate("reputacion", "recomendacion timestamps");;
    if (!updatedUser) {
      return res.status(500).json({ error: "User not found" });
    }
    return res.status(200).json({ message: "User updated", user: updatedUser });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.changePassword = async (req, res, next) => {
  try {
    const { _id } = req.user;
    const { password } = req.body;
    const myUser = await User.findById(_id);
    if (!myUser) {
      return res.status(404).json({ error: "User not found" });
    }
    myUser["contrasenia"] = password;
    const updatedUser = await myUser.save();
    // .populate("reputacion.usuario", "username correo")
    // .populate("reputacion", "recomendacion timestamps");
    if (!updatedUser) {
      return res.status(500).json({ error: "Password not updated" });
    }
    return res.status(200).json({ message: "User password updated" });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.changeReputation = async (req, res, next) => {
  try {
    const { id } = req.params;
    const { addReputacion } = req.body;
    const { toUser } = req.user;
    const user = await User.findById(id);
    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }
    let reputacion = user.reputacion || [];
    const alreadyIndex = reputacion.findIndex((item) =>
      item.user.equals(toUser._id)
    );
    if (addReputacion) {
      // Agregar reputación
      if (alreadyIndex >= 0) {
        // Si ya existe una reputación para el usuario, no hace nada
        return res
          .status(200)
          .json({ message: "Reputation already exists", user });
      }
      // Si no existe una reputación para el usuario, agrega una nueva
      reputacion = [
        ...reputacion,
        {
          user: toUser._id,
          recomendacion: true,
          timestamps: new Date(),
        },
      ];
    } else {
      // Eliminar reputación
      if (alreadyIndex >= 0) {
        // Si ya existe una reputación para el usuario, elimínala
        reputacion.splice(alreadyIndex, 1);
      } else {
        // Si no existe una reputación para el usuario, no hace nada
        return res
          .status(200)
          .json({ message: "Reputation does not exist", user });
      }
    }
    user.reputacion = reputacion;
    const updatedUser = (await user.save())
    //   .populate("reputacion.usuario", "username correo")
    //   .populate("reputacion", "recomendacion timestamps");
    if (!updatedUser) {
      return res.status(500).json({ error: "User not updated" });
    }
    return res
      .status(200)
      .json({ message: "User reputation updated", user: updatedUser });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

module.exports = controller;
