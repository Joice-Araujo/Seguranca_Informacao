import { useState } from "react";
import { Btn } from "../Componentes/btn";
import { Input } from "../Componentes/input";
import { Check } from "../Componentes/inputCheckbox";
import { ISign } from "../Interfaces/ISign";
import { userService } from "../Services/user-service";
import { useNavigate } from "react-router-dom";

export function Cadastro() {

    const [username, setUsername] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [password, setPassword] = useState('');
    const [opcional, setOpcional] = useState<boolean>(false);
    const [obrigatorio, setObrigatorio] = useState<boolean>(false);
    const nav = useNavigate()

    const cadastrarUsuario = async () => {
        const data : ISign = {
            email : userEmail,
            senha : password,
            username : username
        }

        const response = await userService.cadastrarUsuario(data)

        if (response.status == 200) {
            alert("Usuário cadastrado com sucesso")
            nav("/")
        }

    };

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

                <div>

                    <div className="alinhandoCheckbox">
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

                <div className="divBtn">
                    <Btn label="Cadastrar" onClick={cadastrarUsuario} btnClassName="btnLogin" />
                </div>
            </div>
        </div>
    );
}
