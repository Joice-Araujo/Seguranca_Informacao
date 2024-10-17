import { useState } from "react";
import { Btn } from "../Componentes/btn";
import { Input } from "../Componentes/input";
import { Link, useNavigate } from "react-router-dom";
import { LoginType } from "../Interfaces/ILogin";
import useAuth from "../Hooks/useAuth";

export function Login() {
  const [username, setusername] = useState('');
  const [password, setPassword] = useState("");
  const nav = useNavigate()
  const auth = useAuth()

  const logar = async () => {
    const data : LoginType = {senha: password, username: username }

    await auth?.login(data)

    nav("/create-post")
  };

  return (
    <div className="mt-28 grow flex items-center justify-center ">
      <div className=" max-w-sm w-full bg-white p-6 rounded-lg shadow-md border border-gray-500">
        <h2 className="h2">Login</h2>

        <div className="mb-4">
          <Input
            label="Username"
            type="text"
            value={username}
            setValue={setusername}
            labelClassName="labelLogin "
            inputClassName="inputLogin"
          />
        </div>

        <div className="mb-6">
          <Input
            label="Senha"
            type="password"
            value={password}
            setValue={setPassword}
            labelClassName="labelLogin "
            inputClassName="inputLogin"
          />
        </div>

        <div className="text-center">
          <Btn label="Login" onClick={logar} btnClassName="btnLogin" />
        </div>

        <br></br>

        <Link to="/cadastro" >Cadastre-se aqui!</Link>

    </div>
    </div>
  );
}
