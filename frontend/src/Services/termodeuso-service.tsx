import { AssinarTermo, TermosDeUsoDto } from "../Interfaces/TermoDeUso";
import api from "./api";
import { authService } from "./auth-service";

export const termoDeUsoService = {
  getActual: async () => {
    return await api.get("termodeuso/atual");
  },

  assinarTermo: async (data: AssinarTermo) => {
    return await api.put("termodeuso/add-assinante", data);
  },

  getTermoAssinado: async (userId: string) => {
    return await api.get(`termodeuso/assinatura/${userId}`, {
      headers: {
        Authorization: `Bearer ${authService.getToken()}`,
      },
    });
  },

  createTermoDeUso: async (termo: TermosDeUsoDto) => {
    return await api.post("termodeuso/create-termo", termo, {
      headers: {
        Authorization: `Bearer ${authService.getToken()}`,
      },
    });
  },
};
