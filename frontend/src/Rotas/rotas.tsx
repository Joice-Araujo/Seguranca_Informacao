import { Routes, Route } from "react-router-dom";
import { Login } from "../Pages/login";
import { Cadastro } from "../Pages/cadastro";
import { Perfil } from "../Pages/perfil";
import { CriarBlog } from "../Pages/blog";
import { PrivateRoute } from "./private-routes";
import MyPosts from "../Pages/MyPost";
import { Senha } from "../Pages/senha";
import { TermosDeUso } from "../Pages/termosDeUso";

export const Rotas = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/perfil" element={<PrivateRoute page={<Perfil />} />} />
      <Route path="/create-post" element={<PrivateRoute page={<CriarBlog />} />}/>
      <Route path="/my-posts" element={<PrivateRoute page={<MyPosts />} />} />
      <Route path="/senha" element={<PrivateRoute page={<Senha />} />} />
      <Route path="/termos" element={<PrivateRoute page={<TermosDeUso />} />} />
    </Routes>
  );
};

export default Rotas;
