import { useNavigate } from "react-router-dom";
import { authService } from "../Services/auth-service";
import useAuth from "../Hooks/useAuth";
import { FaUser, FaPlus, FaList, FaSignOutAlt } from "react-icons/fa"; 
import { RiLockPasswordFill } from "react-icons/ri";


export function SideBar() {
  const navigate = useNavigate();

  const perfil = () => {
    navigate("/perfil"); // Navega para a página de perfil
  };
  const post = () => {
    navigate("/create-post"); // Navega para a página de criação de post
  };
  const myPosts = () => {
    navigate("/my-posts"); // Navega para a página de visualização dos posts
  };
  const senha = () => {
    navigate("/senha"); // Navega para a página de visualização dos posts
  };

  const auth = useAuth()

  return (

    <div className="relative flex flex-col bg-clip-border rounded-xl bg-white text-gray-700 h-[calc(100vh-0.1rem)] w-full max-w-[20rem] border p-4 shadow-xl shadow-blue-gray-900/5">
      <div className="mb-2 p-4">
        <h5 className="block antialiased tracking-normal font-sans text-xl font-semibold leading-snug text-gray-900">
          {authService.getRole()}
        </h5>
      </div>
      <nav className="flex flex-col gap-1 min-w-[240px] p-2 font-sans text-base font-normal text-gray-700">
        {/* Button 1 */}
        <div
          role="button"
          tabIndex={0}
          onClick={post}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-gray-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            
          </div>
          <FaPlus className="mr-4" />
          Criar Postagem
        </div>

        <div
          role="button"
          tabIndex={0}
          onClick={myPosts}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-gray-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            
          </div>
          <FaList className="mr-4" />
          Minhas Postagens
        </div>

        {/* Button 2 */}
        <div
          role="button"
          tabIndex={0}
          onClick={perfil}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
            <div className="grid place-items-center mr-4">
              
            </div>
            <FaUser className="mr-4" />
            Perfil
        </div>


        {/* Button 4  */}
         <div
          role="button"
          tabIndex={0}
          onClick={senha}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            
          </div>
          <RiLockPasswordFill className="mr-4 w-5 h-5" />
          Atulize Senha
        </div>

        {/* Button 3 */}
        <div
          role="button"
          tabIndex={0}
          onClick={() => auth?.logout()}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            
          </div>
          <FaSignOutAlt className="mr-4" />
          Sair
        </div>
        

      </nav>
    </div>
  )
}