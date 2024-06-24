const { SignJWT, jwtVerify } = require("jose");
const secret = new TextEncoder().encode(process.env.TOKEN_SECRET || "Super Secret Value");
const expTime = process.env.TOKEN_EXP || "30d"

const tools = {
    createToken: async(id) => {
        return await new SignJWT()
            .setProtectedHeader({ alg: "HS256" })
            .setSubject(id)
            .setExpirationTime(expTime)
            .setIssuedAt()
            .sign(secret);
    },
    verifyToken: async(token) => {
        try {
            const { payload } = await jwtVerify(token, secret);
            return payload;
        } catch (error) {
            return false;
        }
    }
};

module.exports = tools;