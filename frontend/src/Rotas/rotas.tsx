import { Routes, Route } from "react-router-dom";
import { Login } from "../Paginas/login";
import { Cadastro } from "../Paginas/cadastro";


export const Rotas = () => {
  return (
    <Routes>


      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />


    </Routes>
  );
};

export default Rotas;