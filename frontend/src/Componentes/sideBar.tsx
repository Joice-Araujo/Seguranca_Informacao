import { useNavigate } from "react-router-dom";


export function SideBar() {
  const navigate = useNavigate();

  const perfil = () => {
    navigate("/perfil"); // Navega para a rota do blog
  };
  const blog = () => {
    navigate("/blog"); // Navega para a rota do blog
  };

  return (

    <div className="relative flex flex-col bg-clip-border rounded-xl bg-white text-gray-700 h-[calc(100vh-0.1rem)] w-full max-w-[20rem] border p-4 shadow-xl shadow-blue-gray-900/5">
      <div className="mb-2 p-4">
        <h5 className="block antialiased tracking-normal font-sans text-xl font-semibold leading-snug text-gray-900">
          Lorem ipsum
        </h5>
      </div>
      <nav className="flex flex-col gap-1 min-w-[240px] p-2 font-sans text-base font-normal text-gray-700">
        {/* Button 1 */}
        <div
          role="button"
          tabIndex={0}
          onClick={blog}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-gray-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            colocar icone aqui
          </div>
          Blog
        </div>

        {/* Button 2 */}
        <div
          role="button"
          tabIndex={0}
          onClick={perfil}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
            <div className="grid place-items-center mr-4">
              colocar icone aqui
            </div>
            Perfil
        </div>

        {/* Button 3 */}
        <div
          role="button"
          tabIndex={0}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            colocar icone aqui
          </div>
          Sair
        </div>
        

        {/* Button 4 */}
        <div
          role="button"
          tabIndex={0}
          className="flex items-center w-full p-3 rounded-lg text-start leading-tight transition-all hover:bg-blue-50 hover:bg-opacity-80 focus:bg-blue-50 focus:bg-opacity-80 active:bg-blue-50 active:bg-opacity-80 hover:text-blue-900 focus:text-blue-900 active:text-blue-900 outline-none"
        >
          <div className="grid place-items-center mr-4">
            colocar icone aqui
          </div>
          Excluir Conta
        </div>

      </nav>
    </div>
  )
}