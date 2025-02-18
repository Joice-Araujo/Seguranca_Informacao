import { useEffect, useState } from "react";
import { Input } from "../Componentes/input";
import { Btn } from "../Componentes/btn";
import { userService } from "../Services/user-service";
import { termoDeUsoService } from "../Services/termodeuso-service";
import { OpcaoTermosDeUsoDto, TermosDeUsoDto } from "../Interfaces/TermoDeUso";
import _ from "lodash";
import { authService } from "../Services/auth-service";
import useAuth from "../Hooks/useAuth";
import { useNavigate } from "react-router-dom";

export function Perfil() {
  const [userId, setUserId] = useState("");
  const [username, setUsername] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [termoDeUso, setTermoDeUso] = useState<OpcaoTermosDeUsoDto[]>([]);
  const [editar, setEditar] = useState(false);
  const [termoDeUsoObrigatorio, setTermoDeUsoObrigatorio] = useState<TermosDeUsoDto>({
    descricao: "",
    opcoes: [],
    versao: "",
    assinado: true
  });
  const [termoAssinado, setTermoAssinado] = useState<boolean>(true);
  const auth = useAuth()
  const nav = useNavigate()

  interface Usuario {
    id: string;
    username: string;
    email: string;
  }

  const perfilUsuario = async () => {
    const resposta = await userService.getUsuarioByUsername();
    const perfil: Usuario = await resposta.data;

    setUserId(perfil.id);
    localStorage.setItem("username", perfil.username);
    localStorage.setItem("email", perfil.email);
    setUserEmail(perfil.email);
    setUsername(perfil.username);

    await termoDeUsoService
      .getTermoAssinado(perfil.id)
      .then((resp) => {
        localStorage.setItem("termo", resp.data.opcoes);
        setTermoDeUso(resp.data.opcoes);
      })
      .catch(async () => {
        setTermoAssinado(false);
        const termoAtual = (await termoDeUsoService.getActual()).data;
        setTermoDeUsoObrigatorio({ ...termoAtual, assinado: false });
        setTermoDeUso(termoAtual.opcoes);
      });
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

      await userService.update(userId, usuarioAtualizado).then(resp => {
        authService.setToken(resp.data.token)
      });

      alert("Usuário atualizado com sucesso!");
      setEditar(false);
    } catch (error) {
      console.error("Erro ao atualizar usuário:", error);
      alert("Erro ao atualizar usuário.");
    }
  };

  const atualizarUsuario = async () => {
    if (!editar) {
      setEditar(!editar);
    } else {
      if (
        !_.isEqual(userEmail, localStorage.getItem("email")) ||
        !_.isEqual(username, localStorage.getItem("username"))
      ) {
        salvarAlteracao();
      }

      if (termoDeUsoObrigatorio.assinado) {
        if (!_.isEqual(termoDeUso, localStorage.getItem("termo"))) {
          const data = { idUsuario: userId, opcoes: termoDeUso };
          console.log(data);
          await termoDeUsoService.assinarTermo(data).then((resp) => {
            if (resp.status == 200) {
              alert("Termo de uso atualizado");
            }
          });
          setTermoAssinado(true)
        }
      } else {
        alert("Termo de uso obrigatório não está assinado")
      }
    }
  };

  const deletarConta = async () => {
    const confirmacao = confirm("Tem certeza que deseja deletar a sua conta?")
    if (confirmacao) {
      userService.excluirConta(userId).then(resp => {
        if (resp.status == 200) {
          alert("Conta Deletada com sucesso")
          authService.removeToken();
          auth?.logout();
          nav("/")
        }
      }).catch(err => {
        alert("Ocorreu um erro ao apagar a sua conta")
        console.error(err)
      });
    }
  }

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
            {!termoAssinado && (
              <>
                <p className="text text-red-600">
                  *Termo de uso obrigatório não assinado
                </p>

                <div className="flex flex-row space-x-2">
                  <div>
                    <input type={"checkbox"} checked={termoDeUsoObrigatorio?.assinado} onChange={() => {
                      if (termoDeUsoObrigatorio?.assinado != null) {
                        setTermoDeUsoObrigatorio({ ...termoDeUsoObrigatorio, assinado: !termoDeUsoObrigatorio.assinado })
                      }
                    }} />
                  </div>

                  <div>
                    <label>{termoDeUsoObrigatorio?.descricao}</label>
                  </div>
                </div>
              </>
            )}
            {termoDeUso.map((termo, index) => {
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
            })}
          </div>

          <div className="flex mt-3 justify-around items-center ">
            <Btn
              label={editar ? "Salvar" : "Editar"}
              onClick={atualizarUsuario}
              btnClassName="btnLogin"
            />
            <Btn label="Apagar" onClick={deletarConta} btnClassName="btnExcluir" />
          </div>
        </div>
      </div>
    </>
  );
}
