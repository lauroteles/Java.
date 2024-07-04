import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './main/App';
import reportWebVitals from './reportWebVitals';
import { createBrowserRouter } from 'react-router-dom';
import Login from './views/Login';
import CadastroUsuario from './views/CadastroDeUsuario';
import { RouterProvider } from 'react-router-dom';
import HOme from './views/Home';
import { default as ConsultaLancamentos } from './views/consultaLancamentos';
import { default as CadastrarLancamentos } from './views/CadastroLancamentos';


const router = createBrowserRouter([
  {
    path: "/",
    element: <App />
  },
  {
    path: "/Login",
    element: <App><Login /></App>
  },
  {
    path: "/cadastro",
    element: <App><CadastroUsuario /></App>
  },
  {path:'/home',
    element: <App>< HOme/></App>
  },
  {path:'/consulta',
    element: <App>< ConsultaLancamentos/></App>
  },
  {path:'/cadastro-lancamento',
    element: <App>< CadastrarLancamentos/></App>
  }


])



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);


