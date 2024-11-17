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
     
    update: async (userId: string,usuarioAtualizado: { username: string; email: string }) => {
        return await api.put(`usuario/${userId}`, usuarioAtualizado, {
            headers: { Authorization: `Bearer ${authService.getToken()}` }
        })
    },

    updateSenha: async (id: string, data: ChangePasword) => {
        return await api.put(`usuario/change-password/${id}`, data, {
            headers: { Authorization: `Bearer ${authService.getToken()}`}
        })
    },

    excluirConta: async (id: string)=> {
        return await api.delete(`usuario/${id}`, {
            headers: {Authorization: `Bearer ${authService.getToken()}`}
        })
    }
}