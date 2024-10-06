import { createContext, useState } from "react"
import { LoginType } from "../Interfaces/ILogin"
import { authService } from "../Services/auth-service"

interface AuthContextType {
    token: string | null
    role: string | null
    login: Function
    logout: Function
}

export const AuthContext = createContext<AuthContextType | null>(null)

export function AuthProvider({ children }: any) {
    const [token, setToken] = useState<string | null>(null)
    const [role, setRole] = useState<string | null>(null)

    const login = async (data: LoginType) => {
        await authService.authenticateUser(data).then(async (resp) => {
            console.log(resp)

            switch (resp.status) {
                case 201:
                    const respToken = resp.data.access_token
                    if (respToken) {
                        authService.setToken(respToken)
                        setToken(respToken)

                        const respRole = authService.decodeToken(respToken)?.role
                        if (respRole) {
                            setRole(respRole)
                        }
                    }
                    break;

                case 401:
                    setToken(null)
                    throw new Error("Login ou senha inválidos")
                default:
                    throw new Error("Erro na validação do usuário")
            }
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

