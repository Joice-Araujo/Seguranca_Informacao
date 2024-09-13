
import { BrowserRouter as Router } from 'react-router-dom';
import './App.css'
import Rotas from './Rotas/rotas';
import { SideBar } from './Componentes/sideBar';

function App() {
  
  return (
    <>
    <Router>
    <SideBar/>
    {/* <Rotas/> */}

    </Router>
    
    </>
  )
}

export default App
