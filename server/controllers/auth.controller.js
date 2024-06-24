const { createToken, verifyToken } = require("../utils/jwt.tools");
const ROLES = require("../data/roles.constants.json");

const User = require("../models/usuario.model");
const Articulo = require("../models/articulo.model");

const controller = {};

controller.register = async (req, res, next) => {
  try {
    const { username, email, password, isEmprendedor } = req.body;
    const user = await User.findOne({
      $or: [{ username: username }, { correo: email }],
    });
    if (user) {
      return res.status(409).json({ error: "User already exists!" });
    }
    if(isEmprendedor){
      const newUser = new User({
        username: username,
        correo: email,
        contrasenia: password,
        roles: [ROLES.EMPRENDE]
      });
      await newUser.save();
      return res.status(201).json({ message: "Emprendimiento registrado" });
    }else{
      const newUser = new User({
        username: username,
        correo: email,
        contrasenia: password,
        roles: [ROLES.USER]
      });
      await newUser.save();
      return res.status(201).json({ message: "Usuario registrado" });
    }
    
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
    const { _id, username, correo, roles, profile_pic, desc, whatsapp, wishlist } = req.user;
    return res.status(200)
      .json({ _id, username, correo, roles, profile_pic, desc, whatsapp, wishlist });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.findOneUser = async (req, res, next) => {
  try {
    const { id } = req.query;
    const user = await User.findById(id);
    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }
    return res.status(200).json({ user });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.findAllEmprendimientos = async (req, res, next) => {
  try {
    const users = await User.find({ hidden: false });
    const emprendimientos = users.map(user => {
      const roles = user.roles;
      return roles.includes(ROLES.EMPRENDE) ? user : null;
    })
    emprendimientos.filter((_user) => _user)
    return res.status(200).json({ emprendimientos });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.updateUser = async (req, res, next) => {
  try {
    const { _id } = req.user;
    const { username, picture, desc, whatsapp} = req.body;
    const updatedUser = await User.findByIdAndUpdate(
      _id,
      {
        username: username,
        profile_pic: picture,
        desc: desc,
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
    const myUser = await User.findById(_id);
    if (!myUser) {
      return res.status(404).json({ error: "User not found" });
    }
    myUser["contrasenia"] = password;
    const updatedUser = await myUser.save();
    if (!updatedUser) {
      return res.status(500).json({ error: "Password not updated" });
    }
    return res.status(200).json({ message: "User password updated" });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

controller.editWishlist = async (req, res, next) => {
  try {
    const { articuloId } = req.query;
    const user = req.user; // El usuario autenticado se obtiene del middleware
    // Verificar que el artículo exista
    const articulo = await Articulo.findById(articuloId);
    if (!articulo) {
      return res.status(404).json({ error: 'Artículo no encontrado' });
    }
    // Verificar si el artículo ya está en la wishlist del usuario
    const wishlistItemIndex = user.wishlist.findIndex(item => item.user.toString() === articuloId);
    if (wishlistItemIndex !== -1) {
      // Si el artículo ya está en la wishlist, eliminarlo
      user.wishlist.splice(wishlistItemIndex, 1);
      await user.save();
      return res.status(200).json({ message: 'Artículo eliminado de la wishlist', wishlist: user.wishlist });
    }
    // Si el artículo no está en la wishlist, agregarlo
    user.wishlist.push({ user: articuloId, timestamps: new Date() });
    await user.save();
    return res.status(200).json({ message: 'Artículo agregado a la wishlist', wishlist: user.wishlist });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal Server Error" });
  }
};

module.exports = controller;
