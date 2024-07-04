

import Login from '../views/Login';
import CadastroUsuario from '../views/CadastroDeUsuario';
import Navibar from '../components/NavBar';


import 'bootswatch/dist/pulse/bootstrap.css';
import "../custom.css";
import '../toastr.css';
import 'primereact/resources/themes/saga-blue/theme.css'; // exemplo de tema disponível
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

import React, { Component } from 'react';
import {mensagemErro} from '../components/toastr'

class App extends React.Component {
  render() {
    return(
        
    
    <div className='container'>
      <Navibar />
      {this.props.children}

    </div>);
  }


}

export default App;
