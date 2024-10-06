import { Routes, Route } from "react-router-dom";
import { Login } from "../Paginas/login";
import { Cadastro } from "../Paginas/cadastro";
import { Perfil } from "../Paginas/perfil";
import { Blog } from "../Paginas/blog";
import { PrivateRoute } from "./private-routes";


export const Rotas = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<PrivateRoute page={<Cadastro />}/>} /> 
      <Route path="/perfil" element={<PrivateRoute page={<Perfil />}/>} /> 
      <Route path="/blog" element={<PrivateRoute page={<Blog />}/>} />
      <Route path="/perfil" element={<PrivateRoute page={<Perfil />}/>} />
    </Routes>
  );
};

export default Rotas;