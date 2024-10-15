import { jwtDecode, JwtPayload} from "jwt-decode"
import { LoginType } from "../Interfaces/ILogin"
import api from "./api"

const token = "blog"

export const authService = {
    async authenticateUser(data : LoginType){
       return await api.post("/login", data)
    },

    setToken (data : any) {
        localStorage.setItem(token, data);
    },

    getToken(){
        return localStorage.getItem(token)
    },

    removeToken() {
        localStorage.removeItem(token)
    },

    decodeToken(token : string | null | undefined){
        if (token) {
            const decode = jwtDecode(token)
            return decode
        }
        return null
    },

    getRole(){
        const token = this.getToken()
        if (token) {
            const username = jwtDecode<JwtPayload>(token)?.sub
            return typeof username == "string" ? username : null
        }
        return null;
    }
}