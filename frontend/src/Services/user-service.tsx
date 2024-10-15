import { ISign } from "../Interfaces/ISign";
import api from "./api";

export const userService = {
    cadastrarUsuario : async (data : ISign) => {
        return await api.post("/register", data)
    }
}