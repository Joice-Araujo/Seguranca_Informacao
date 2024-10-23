import { CreateBlog } from "../Interfaces/CreateBlog";
import api from "./api";
import { authService } from "./auth-service";

export const blogService = {
    createBlog: async (data: CreateBlog) => {
        console.log(data)
        try {

            await fetch("http://localhost:8080/postagem", {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "Authorization": `Bearer ${authService.getToken()}`,
                    "Content-Type": "application/json"
                }
            }).then(response => {
                if (!response.ok) {
                    // Lida com erros HTTP (4xx, 5xx)
                    throw new Error(`Erro HTTP! Status: ${response.status}`);
                }
                console.log(response.json());
                alert("Post criado com sucesso");
            })
                .then(data => {
                    console.log("Resposta do servidor:", data);
                })
                .catch(error => {
                    console.error("Erro ao realizar a requisição:", error);
                });


        } catch (error: any) {
            // Se o erro for de autenticação, você pode verificar o código do status
            if (error.response && error.response.status === 403) {
                alert("Sua sessão está expirado! \nRealize o login novamente");
                authService.removeToken();
            } else {
                alert("Houve um erro ao postar o blog");
                console.error("Erro ao criar o blog:", error);
            }
        }
    },

    getBlogByUser: async () => {
        return await api.get(`postagem/user`, {
            headers: { Authorization: `Bearer ${authService.getToken()}` }
        })
    }
};
