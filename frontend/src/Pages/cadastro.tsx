import { useEffect, useState } from "react";
import { Btn } from "../Componentes/btn";
import { Input } from "../Componentes/input";
import { ISign } from "../Interfaces/ISign";
import { userService } from "../Services/user-service";
import { useNavigate } from "react-router-dom";
import { termoDeUsoService } from "../Services/termodeuso-service";
import { OpcaoTermosDeUsoDto, TermosDeUsoDto } from "../Interfaces/TermoDeUso";
import { Check } from "../Componentes/inputCheckbox";
import useAuth from "../Hooks/useAuth";
import { authService } from "../Services/auth-service";

export function Cadastro() {
  const [username, setUsername] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");
  const [termodeuso, setTermodeuso] = useState<TermosDeUsoDto>({
    descricao: "",
    opcoes: [],
    versao: "",
  });
  const [termoAssinado, setTermoAssinado] = useState<boolean>(false);

  const auth = useAuth();

  const nav = useNavigate();

  const cadastrarUsuario = async () => {
    if (!termoAssinado) {
      alert(`${termodeuso.descricao} é obrigatório`);
      return;
    }

    if (username == "" || userEmail == "" || password == "") {
      console.log(username);
      console.log(userEmail);
      console.log(password);
      alert("Todos os campos são necessários");
      return;
    }

    const data: ISign = {
      email: userEmail,
      senha: password,
      username: username,
    };

    const response = await userService.cadastrarUsuario(data);

    console.log(response.data);

    authService.setToken(response.data.token);

    if (username != null) {
      await userService.getUsuarioByUsername().then(async (resp) => {
        const user = resp.data;
        await termoDeUsoService
          .assinarTermo({
            idUsuario: user.id,
            opcoes: termodeuso.opcoes,
          })
          .then((resp) => {
            if (response.status == 200 && resp.status == 200) {
              alert("Usuário cadastrado com sucesso");
              nav("/");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      });
    }
  };

  const toggleAceito = (index: number) => {
    const novaOpcoes = [...termodeuso.opcoes];
    novaOpcoes[index].aceito = !novaOpcoes[index].aceito;

    setTermodeuso({
      ...termodeuso,
      opcoes: novaOpcoes,
    });
  };

  useEffect(() => {
    termoDeUsoService.getActual().then((resp) => {
      setTermodeuso(resp.data);
    });
  }, []);

  return (
    <div className="mt-28 grow flex items-center justify-center">
      <div className=" max-w-sm w-full bg-white p-6 rounded-lg shadow-md border  border-gray-500">
        <h2 className="h2">Cadastre-se</h2>

        <div className="mb-4">
          <Input
            label="Username"
            type="text"
            value={username}
            setValue={setUsername}
            labelClassName="labelLogin"
            inputClassName="inputLogin"
          />
        </div>

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

        {termodeuso.descricao != "" && (
          <div>
            <div className="alinhandoCheckbox">
              <Check
                label={termodeuso.descricao + " (obrigatório)"}
                type="checkbox"
                value={termoAssinado}
                setValue={() => setTermoAssinado(!termoAssinado)}
                labelClassName=""
                inputClassName=""
              />
            </div>

            {termodeuso.opcoes.map((termo, index) => {
              return (
                <Check
                  key={index}
                  label={termo.descricao}
                  type="checkbox"
                  value={termo.aceito}
                  setValue={() => toggleAceito(index)}
                  labelClassName=""
                  inputClassName=""
                />
              );
            })}
          </div>
        )}

        <div className="divBtn">
          <Btn
            label="Cadastrar"
            onClick={cadastrarUsuario}
            btnClassName="btnLogin"
          />
        </div>
      </div>
    </div>
  );
}
