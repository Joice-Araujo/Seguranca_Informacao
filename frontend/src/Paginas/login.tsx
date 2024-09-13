import { useState } from "react";
import { Btn } from "../Componentes/btn";
import { Input } from "../Componentes/input";
import { Link } from "react-router-dom";

export function Login() {
  const [userEmail, setUserEmail] = useState('');
  const [password, setPassword] = useState('');

  const logar = () => {

  };

  return (
    <div className="mt-28 flex items-center justify-center">
      <div className=" max-w-sm w-full bg-white p-6 rounded-lg shadow-md border  border-gray-500">
        <h2 className="text-center text-3xl font-semibold mb-6 text-gray-700">Login</h2>

        <div className="mb-4">
          <Input
            label="E-mail"
            type="text"
            value={userEmail}
            setValue={setUserEmail}
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
