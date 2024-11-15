import { AssinarTermo } from "../Interfaces/TermoDeUso";
import api from "./api";
import { authService } from "./auth-service";

export const termoDeUsoService = {
  getActual: async () => {
    return await api.get("termodeuso/atual");
  },

  assinarTermo: async (data: AssinarTermo) => {
    return await api.put("termodeuso/add-assinante", data);
  },

  getTermoAssinado: async (userId : string) => {
    return await api.get(`termodeuso/assinatura/${userId}`, {
      headers: {
        Authorization: `Bearer ${authService.getToken()}`,
      },
    });
  },
};
