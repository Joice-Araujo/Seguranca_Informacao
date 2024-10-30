import { useEffect, useState } from "react";
import { Input } from "../Componentes/input";
import { Btn } from "../Componentes/btn";
import { Check } from "../Componentes/inputCheckbox";
import { userService } from "../Services/user-service";
import useAuth from "../Hooks/useAuth";



export function Perfil() {
    const [username, setUsername] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [opcional, setOpcional] = useState<boolean>(false);
    const [obrigatorio, setObrigatorio] = useState<boolean>(false);
    const [editar, setEditar] = useState(false);

    const auth = useAuth()

    interface Usuario {
        username: string,
        email: string,
    }


    const perfilUsuario = async () => {

        try {
            const resposta = await userService.getUsuarioByUsername()
            const perfil: Usuario = await resposta.data;

            setUsername(perfil.username);
            setUserEmail(perfil.email);
        } catch (error) {
            console.error("Erro ao buscar os dados do usuário:", error);
        }
    };

   
    const salvarAlteracao = async () => {
        try {
           
            const usuarioAtualizado = {
                username: username,
                email: userEmail,
            };

            
            await userService.update(usuarioAtualizado);

            alert("Usuário atualizado com sucesso!");
            setEditar(false); 
        } catch (error) {
            console.error("Erro ao atualizar usuário:", error);
            alert("Erro ao atualizar usuário.");
        }
    }

    const atualizarUsuario = async () => {
        if (editar) {
            salvarAlteracao();
        } else {
            setEditar(true); 
        }
    }
   

    useEffect(() => {
        perfilUsuario()

    }, [])

    


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

                        <div className="alinhandoCheckbox mt-6">
                            <Check
                                label="mensagens promocionais opcional "
                                type="checkbox"
                                value={opcional}
                                setValue={setOpcional}
                                labelClassName=""
                                inputClassName=""
                            />
                        </div>

                        <Check
                            label="termos obrigatórios"
                            type="checkbox"
                            value={obrigatorio}
                            setValue={setObrigatorio}
                            labelClassName=""
                            inputClassName=""
                        />
                    </div>

                    <div className="flex mt-3 justify-around items-center ">
                        <Btn label={editar ? "Salvar" : "Editar"} onClick={atualizarUsuario} btnClassName="btnLogin" />
                        {/* <Btn label="Editar" onClick={editarUsuario} btnClassName="btnLogin" /> */}
                    </div>
                </div>
            </div>
        </>
    )
}