import { useEffect, useState } from "react";
import { Input } from "../Componentes/input";
import { Btn } from "../Componentes/btn";
import { userService } from "../Services/user-service";
import useAuth from "../Hooks/useAuth";
import { termoDeUsoService } from "../Services/termodeuso-service";
import { OpcaoTermosDeUsoDto } from "../Interfaces/TermoDeUso";
import _, { set } from "lodash";


export function Perfil() {
  const [userId, setUserId] = useState("")
  const [username, setUsername] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [termoDeUso, setTermoDeUso] = useState<OpcaoTermosDeUsoDto[]>([]);
  const [editar, setEditar] = useState(false);


  interface Usuario {
    id: string;
    username: string;
    email: string;
  }

  const perfilUsuario = async () => {
    try {
      const resposta = await userService.getUsuarioByUsername();
      const perfil: Usuario = await resposta.data;
      const termo = await termoDeUsoService.getTermoAssinado(perfil.id);
      setUserId(perfil.id)
      localStorage.setItem("termo", termo.data.opcoes);
      localStorage.setItem("username", perfil.username)
      localStorage.setItem("email", perfil.email)
      setTermoDeUso(termo.data.opcoes);
      setUsername(perfil.username);
      setUserEmail(perfil.email);
    } catch (error) {
      console.error("Erro ao buscar os dados do usuário:", error);
    }
  };

  const handleOpcao = (index: number) => {
    const novoValorTermo: OpcaoTermosDeUsoDto[] = [...termoDeUso];
    novoValorTermo[index].aceito = !novoValorTermo[index].aceito;
    setTermoDeUso(novoValorTermo);
  };

  const salvarAlteracao = async () => {
    try {
      const usuarioAtualizado = {
        username: username,
        email: userEmail,
      };

      await userService.update(userId,usuarioAtualizado);

      alert("Usuário atualizado com sucesso!");
      setEditar(false);
    } catch (error) {
      console.error("Erro ao atualizar usuário:", error);
      alert("Erro ao atualizar usuário.");
    }
  };

  const atualizarUsuario = async () => {
    if (!editar) {
        setEditar(!editar)
    } else {
        if (!_.isEqual(userEmail, localStorage.getItem("email")) || !_.isEqual(username, localStorage.getItem("username"))) {
          salvarAlteracao();
        }
    
        if (!_.isEqual(termoDeUso, localStorage.getItem("termo"))) {
            const data = {idUsuario: userId,opcoes: termoDeUso}
            console.log(data)
            await termoDeUsoService.assinarTermo(data).then(resp => {
                if (resp.status == 200) {
                    alert("Termo de uso atualizado")
                }
            })
        }
    }
  };

  useEffect(() => {
    perfilUsuario();
  }, []);

  return (
    <>
      <div className="flex flex-col mx-auto justify-center items-center">
        <div className=" max-w-sm w-full bg-white p-6 rounded-lg shadow-md border  border-gray-500">
          <h2 className="h2">Seu Perfil</h2>

          <div className="mb-4">
            <Input
              label="Username"
              type="text"
              value={username}
              setValue={setUsername}
              disabled={!editar}
              labelClassName="labelLogin"
              inputClassName="inputLogin"
              //onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className="mb-4">
            <Input
              label="E-mail"
              type="text"
              value={userEmail}
              setValue={setUserEmail}
              disabled={!editar}
              labelClassName="labelLogin "
              inputClassName="inputLogin"
              //onChange={(e) => setUserEmail(e.target.value)}
            />
          </div>

          <div>
            {
                termoDeUso.map((termo, index) => {
                  return (
                    <div className="flex flex-row space-x-2">
                      <div>
                        <input
                          type={"checkbox"}
                          onChange={() => {
                            handleOpcao(index);
                          }}
                          checked={termo.aceito}
                        />
                      </div>

                      <div>
                        <label>{termo.descricao}</label>
                      </div>
                    </div>
                  );
                })
            }
          </div>

          <div className="flex mt-3 justify-around items-center ">
            <Btn
              label={editar ? "Salvar" : "Editar"}
              onClick={atualizarUsuario}
              btnClassName="btnLogin"
            />
            {/* <Btn label="Editar" onClick={editarUsuario} btnClassName="btnLogin" /> */}
          </div>
        </div>
      </div>
    </>
  );
}
