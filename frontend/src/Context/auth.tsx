import { createContext, useState } from "react"
import { LoginType } from "../Interfaces/ILogin"
import { authService } from "../Services/auth-service"
import { useNavigate, useNavigation } from "react-router-dom"

interface AuthContextType {
    token: string | null
    role: string | null
    login: Function
    logout: Function
}

export const AuthContext = createContext<AuthContextType | null>(null)

export function AuthProvider({ children }: any) {
    const [token, setToken] = useState<string | null>(authService.getToken())
    const [role, setRole] = useState<string | null>(authService.getRole())

    const login = async (data: LoginType) => {
        await authService.authenticateUser(data).then(async (resp) => {
            console.log(resp)

            switch (resp.status) {
                case 200:
                    const respToken = resp.data.token
                    if (respToken) {
                        authService.setToken(respToken)
                        setToken(respToken)

                        const respRole = authService.decodeToken(respToken)?.sub
                        if (respRole) {
                            setRole(respRole)
                        }
                    }
                    break;
                default:
                    alert("Erro na validação do usuário")
            }
        }).catch(err => {
            console.log(err)
            setToken(null)
            alert("Login ou senha inválidos")
        })
    }

    const logout = () => {
        authService.removeToken()
        setToken(null)
        setRole(null)
    }

    return (
        <AuthContext.Provider value={{ token, login, logout, role }}>{children}</AuthContext.Provider>
    )

}

