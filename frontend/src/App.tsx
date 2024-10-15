
import { BrowserRouter as Router } from 'react-router-dom';
import './App.css'
import Rotas from './Rotas/rotas';
import { AuthProvider } from './Context/auth';

function App() {

  return (
    <AuthProvider>
        <Router>
          <div className="flex">
            <Rotas />
            {/* <Perfil /> */}
          </div>
        </Router>
    </AuthProvider>
  )
}

export default App
