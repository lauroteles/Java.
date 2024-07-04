import React from "react";
import UsuarioService from "../app/service/usuarioService";
import localStorageServ from "../app/service/localStorageService";

class HOme extends React.Component{
    state = {saldo: 0}

    componentDidMount() {
        const usuarioLogado = localStorage.getItem('_usuario_logado')
        const usuarioLogadoObjeto = localStorageServ.obterItem('_usuario_logado')  
        const usuarioService = new UsuarioService();
        
console.log(usuarioLogadoObjeto)

        usuarioService.obterSaldoPorUsuario(`${usuarioLogadoObjeto.id}`
    ).then( response => {
        this.setState({saldo: response.data})
    }).catch(error => {
        console.error(error.response)
    });
    }
    render(){


        return(
            <div class="jumbotron">
            <h1 class="display-3">Bem vindo!</h1>
            <p class="lead">Esse é seu sistema de finanças.</p>
            <p class="lead">Seu saldo para o mês atual é de R$ {this.state.saldo}</p>
            <hr class="my-4" />
            <p>E essa é sua área administrativa, utilize um dos menus ou botões abaixo para navegar pelo sistema.</p>
            <p class="lead">

            <a class="btn btn-primary btn-lg"
             href="https://bootswatch.com/flatly/#" role="button"><i class="fa fa-users"></i>  
             Cadastrar Usuário</a>
             
            <a class="btn btn-danger btn-lg" 
            href="https://bootswatch.com/flatly/#" role="button"><i class="fa fa-users"></i>  
            Cadastrar Lançamento</a>
            </p>
        </div>
        )
    
    }

}

export default HOme;
