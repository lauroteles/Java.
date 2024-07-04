import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { mensagemErro,mensagemAlerta,mensagemSucesso} from '../components/toastr'

import Card from "../components/card";
import FormGroup from "../components/Form-goup";
import UsuarioService from "../app/service/usuarioService";
import localStorageServ from "../app/service/localStorageService";



const Login = () => {

    
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const navigate = useNavigate();
    const usuarioService = new UsuarioService();
    

    const entrar = () => {
        usuarioService.autenticar({email,senha})
            .then(response => {
                localStorageServ.addItem('_usuario_logado',(response.data))
                localStorage.setItem('_usuario_logado', JSON.stringify(response.data))
                navigate('/home');
                mensagemSucesso('Login efetuado com sucesso!')
            })
            .catch(error => {
                if (error.response && error.response.data) {
                    mensagemErro(error.response.data);
                } else {
                    mensagemAlerta('Erro desconhecido');
                }
            });
    };

    const prepareCadastrar = () => {
        navigate('/cadastro-usuarios');
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6" style={{ position: 'relative', left: '300px' }}>
                    <Card title="Login">
                        <div className="row">
                            <div className="col-lg-12">
                                <div className="bs-component">
                                    <fieldset>
                                        <FormGroup label="Email: *" htmlFor="exampleinputEmail1">
                                            <input 
                                                value={email}
                                                onChange={e => setEmail(e.target.value)} 
                                                type="email" 
                                                className="form-control" 
                                                id="exampleinputEmail1" 
                                                placeholder="Digite o email" 
                                                aria-describedby="emailHelp" 
                                            />                                    
                                        </FormGroup>

                                        <FormGroup label="Senha: *" htmlFor="exampleInputPassword1">
                                            <input 
                                                type="password" 
                                                value={senha}
                                                onChange={e => setSenha(e.target.value)}
                                                className="form-control" 
                                                id="exampleInputPassword1" 
                                                placeholder="Password" 
                                            />
                                        </FormGroup>
                                        <button onClick={entrar} className="btn btn-success">Entrar</button>
                                        <button onClick={prepareCadastrar} className="btn btn-danger">Cadastrar</button>
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                    </Card>
                </div>
            </div>
        </div>
    );
};

export default Login;