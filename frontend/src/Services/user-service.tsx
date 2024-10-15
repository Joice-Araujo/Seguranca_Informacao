import { LoginType } from "../Interfaces/ILogin";
import { ISign } from "../Interfaces/ISign";
import api from "./api";

export const userService = {
    cadastrarUsuario : async (data : ISign) => {
        return await api.post("/register", data)
    },

    // loginUsuario : async (data : LoginType) => {
    //     const response = await api.post("/login", data)
    //     return response
    // }
}