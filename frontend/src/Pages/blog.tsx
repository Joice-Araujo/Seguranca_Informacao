import { useState } from "react";
import { Input } from "../Componentes/input";
import { Btn } from "../Componentes/btn";
import { blogService } from "../Services/blog-service";
import { BlogType } from "../Interfaces/CreateBlog";
import { TextArea } from "../Componentes/textArea";
import { authService } from "../Services/auth-service";
import useAuth from "../Hooks/useAuth";



export function CriarBlog() {
    const [titulo, setTitulo] = useState('');
    const [conteudo, setConteudo] = useState('');
    const auth = useAuth();


    const postar = async () => {
        const username = authService.getRole()

        if (username) {
            const data: BlogType = {
                conteudo: conteudo,
                titulo: titulo,
                username: username
            }
            blogService.createBlog(data)
            return
        }
        alert("Usuário não autenticado! por favor realize o login novamente")
        auth?.logout()
    }

    return (
        <>
            <div className="flex flex-col mx-auto">
                <div className="flex mx-auto">
                    <Input label="Título" type="text" value={titulo} setValue={setTitulo} labelClassName="text-3xl text-center" inputClassName="border-slate-500 solid border-2 rounded-xl p-2 w-96" ></Input>
                </div>

                <div className="flex justify-center mt-5">
                    <TextArea cols={100} rows={25} labelClassname="text-3xl text-center" inputClassname="p-5 solid border-4 border-slate-500 resize-none rounded-3xl" setValue={setConteudo} value={conteudo} label="Conteúdo" />
                </div>

                <div className="flex justify-end ">
                    <Btn btnClassName="bg-white w-48 rounded-l solid border-2 border-slate-200 mt-2 h-8" label="Salvar" onClick={() => postar()}></Btn>
                </div>
            </div>
        </>

    )
}