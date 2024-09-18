import { Routes, Route } from "react-router-dom";
import { Login } from "../Paginas/login";
import { Cadastro } from "../Paginas/cadastro";
import { Perfil } from "../Paginas/perfil";
import { Blog } from "../Paginas/blog";


export const Rotas = () => {
  return (
    <Routes>


      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} /> 
      <Route path="/perfil" element={<Perfil />} /> 
      <Route path="/blog" element={<Blog />} />
      <Route path="/perfil" element={<Perfil />} />



    </Routes>
  );
};

export default Rotas;