import { useEffect, useState } from "react";
import { Input } from "../Componentes/input";
import { Btn } from "../Componentes/btn";
import { userService } from "../Services/user-service";
import useAuth from "../Hooks/useAuth";

export function Senha() {
    const [senhaAtual, setSenhaAtual] = useState("");
    const [novaSenha, setNovaSenha] = useState("");
    const [confirmacaoSenha, setConfirmacaoSenha] = useState("");
    const [id, setId] = useState("");
    const auth = useAuth()

const atualizarSenha = async () => {
    if ((novaSenha == "" || confirmacaoSenha == "" || senhaAtual == "")) {
        alert("Todos os campos devem ser preenchidos")
        return
    }

    if (novaSenha != confirmacaoSenha) {
        alert("A nova senha e a senha de confirmação devem ser iguais")
        return
    }

    await userService.updateSenha(id, { novaSenha: novaSenha, senhaAtual: senhaAtual })
        .then(() => alert("Senha cadastrada com sucesso!")).catch((error) => {
            if (error.status == 409) {
                alert("Senha atual está incorreta!")
            }
            if (error.status == 404) {
                alert("Houve um erro ao realizar seu login!")
                auth?.logout()
            }
        })
}

useEffect(() => {
    userService.getUsuarioByUsername().then(resp => {
        setId(resp.data.id)
    }).catch(() => {
        auth?.logout();
    })
}, [])



return(
    <>
    <div className="flex flex-col mx-auto justify-center items-center">
        <div className=" max-w-sm w-full bg-white p-6 rounded-lg shadow-md border  border-gray-500">
            <h2 className="h2">Seu Perfil</h2>

            <div className="mb-4">
                <Input
                    label="Senha Atual"
                    type="text"
                    value={senhaAtual}
                    setValue={setSenhaAtual}
                    labelClassName="labelLogin"
                    inputClassName="inputLogin"
                    
                />
            </div>

            <div className="mb-4">
                <Input
                    label="Nova senha" 
                    type="text"
                    value={confirmacaoSenha}
                    setValue={setConfirmacaoSenha}
                    labelClassName="labelLogin "
                    inputClassName="inputLogin"
                    
                />
            </div>

            <div className="mb-4">
                <Input
                    label="Confirme a senha"
                    type="text"
                    value={novaSenha}
                    setValue={setNovaSenha}
                    labelClassName="labelLogin "
                    inputClassName="inputLogin"
                    //onChange={(e) => setUserEmail(e.target.value)}
                />
            </div>


            <div className="flex mt-3 justify-around items-center ">
                 <Btn label= "Atualizar Senha" onClick={atualizarSenha} btnClassName="btnLogin" />
            </div>
        </div>
    </div>
</>
)

}