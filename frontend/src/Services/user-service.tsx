import { LoginType } from "../Interfaces/ILogin";
import { ISign } from "../Interfaces/ISign";
import api from "./api";
import { authService } from "./auth-service";
import { ChangePasword } from "../Interfaces/IChangePassword";


export const userService = {
    cadastrarUsuario : async (data : ISign) => {
        return await api.post("/register", data)
    },

    getUsuarioByUsername: async () => {
        console.log(authService.getRole())
        return await api.get(`usuario/username/${authService.getRole()}`, {
            headers: { Authorization: `Bearer ${authService.getToken()}` }
        })
    },
     
    update: async (usuarioAtualizado: { username: string; email: string }) => {
        return await api.put(`usuario/${authService.getRole()}`, usuarioAtualizado, {
            headers: { Authorization: `Bearer ${authService.getToken()}` }
        })
    },

    updateSenha: async (id: string, data: ChangePasword) => {
        return await api.put(`usuario/change-password/${id}`, data, {
            headers: { Authorization: `Bearer ${authService.getToken()}`}
        })
    },

}