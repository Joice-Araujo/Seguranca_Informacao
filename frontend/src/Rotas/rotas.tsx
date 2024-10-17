import { Routes, Route } from "react-router-dom";
import { Login } from "../Pages/login";
import { Cadastro } from "../Pages/cadastro";
import { Perfil } from "../Pages/perfil";
import { Blog } from "../Pages/blog";
import { PrivateRoute } from "./private-routes";


export const Rotas = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} /> 
      <Route path="/perfil" element={<PrivateRoute page={<Perfil />}/>} /> 
      <Route path="/blog" element={<PrivateRoute page={<Blog />}/>} />
    </Routes>
  );
};

export default Rotas;