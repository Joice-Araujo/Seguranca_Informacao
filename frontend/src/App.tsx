
import { BrowserRouter as Router } from 'react-router-dom';
import './App.css'
import Rotas from './Rotas/rotas';
import { SideBar } from './Componentes/sideBar';
import { Perfil } from './Paginas/perfil';

function App() {

  return (
    <>

      <Router>
        <div className="flex">
          <SideBar />
          <Rotas/>
          {/* <Perfil /> */}
        </div>
      </Router>

    </>
  )
}

export default App
