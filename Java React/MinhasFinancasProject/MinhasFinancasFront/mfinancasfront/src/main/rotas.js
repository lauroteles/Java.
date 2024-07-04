import React from "react";
import { HashRouter as Router, Route, Switch } from 'react-router-dom';
import Login from "../views/Login";
import CadastroUsuario from "../views/CadastroDeUsuario";



function Rotas () {
    return (
        <Router>
                <Route path="/login" component={Login} />
                <Route path="/cadastro" component={CadastroUsuario} />

        </Router>
    )
}

export default Rotas;

