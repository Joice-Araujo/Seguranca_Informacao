import { useState } from "react";
import { Input } from "../Componentes/input";
import { Btn } from "../Componentes/btn";
import { SideBar } from "../Componentes/sideBar";



export function Perfil() {
    const [username, setUsername] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [password, setPassword] = useState('');

    const editar = () => { };
    const excluir = () => { };

    return (
        <>
        <SideBar/>
            <div className="flex-1 flex items-center justify-center min-h-screen">
                <div className=" max-w-sm w-full bg-slate-100 p-6 rounded-lg shadow-md  border-gray-500">
                    <h2 className="h2">Seu Perfil</h2>

                    <div className="mb-4">
                        <Input
                            label="Nome"
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


                    <div className="flex flex-row justify-between">


                        <div className="text-center">
                            <Btn label="Editar perfil" onClick={editar} btnClassName="btnLogin" />
                        </div>

                    </div>


                    <div className="mb-6 mt-3">
                        <Input
                            label="Senha"
                            type="password"
                            value={password}
                            setValue={setPassword}
                            labelClassName="labelLogin "
                            inputClassName="inputLogin"
                        />
                    </div>

                    <div className="flex flex-row justify-between">

                        <div className="text-center">
                            <Btn label="Editar senha" onClick={editar} btnClassName="btnLogin" />
                        </div>

                    </div>



                </div>
            </div>
        </>

    )
}