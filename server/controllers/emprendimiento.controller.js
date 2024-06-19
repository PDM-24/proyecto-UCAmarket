const { createToken, verifyToken } = require("../utils/jwt.tools");
const Emprendimiento = require("../models/emprendimiento.model");

const controller = {};
controller.register = async (req, res, next) => {
  try {
    const { username, email, password } = req.body;
    const user = await Emprendimiento.findOne({
      $or: [{ nombre: username }, { correo: email }],
    });
    if (user) {
      return res.status(409).json({ error: "User already exists!" });
    }
    const newUser = new Emprendimiento({
      nombre: username,
      correo: email,
      contrasenia: password
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
    const user = await Emprendimiento.findOne({ correo: email });
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
    const { _id, nombre, correo, profile_pic, whatsapp, descripcion} = req.emprendimiento;
    return res
      .status(200)
      .json({ _id, nombre, correo, profile_pic, whatsapp, descripcion });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.findOneUser = async (req, res, next) => {
  try {
    const { id } = req.params;
    const user = await Emprendimiento.findById(id);
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
    const emprendimientos = await Emprendimiento.find({ hidden: false }, undefined, {
      sort: [{ createdAt: -1 }],
      limit: pagination ? limit : undefined,
      skip: pagination ? offset : undefined,
    });
    return res
      .status(200)
      .json({
        emprendimientos,
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
    const { username, picture, desc, whatsapp } = req.body;
    const updatedUser = await Emprendimiento.findByIdAndUpdate(
      _id,
      {
        nombre: username,
        profile_pic: picture,
        descripcion: desc,
        whatsapp: whatsapp
      },
      { new: true }
    );
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
    const myUser = await Emprendimiento.findById(_id);
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

module.exports = controller;
