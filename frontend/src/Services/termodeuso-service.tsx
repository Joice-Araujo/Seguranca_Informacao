import { AssinarTermo, TermosDeUsoDto } from "../Interfaces/TermoDeUso";
import api from "./api";

export const termoDeUsoService = {
  getActual: async () => {
    return await api.get("termodeuso/atual");
  },

  assinarTermo: async (data: AssinarTermo) => {
    return await api.put("termodeuso/add-assinante", data);
  },
};
