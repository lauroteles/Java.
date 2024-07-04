import React, { useState } from "react";
import Card from "../components/card";
import FormGroup from "../components/Form-goup";
import UsuarioService from "../app/service/usuarioService";
import { mensagemSucesso, mensagemErro } from '../components/toastr';
import { useNavigate } from 'react-router-dom';

const CadastroUsuario = () => {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [senhaRepeticao, setSenhaRepeticao] = useState('');
    const navigate = useNavigate();
    const usuarioService = new UsuarioService();

    const cadastrar = () => {
        const usuario = { nome, email, senha, senhaRepeticao };
        
        

        if(!nome){
            mensagemErro('O nome é obrigatório');
        }
        if (!email) {
            mensagemErro('O email é obrigatório');
            return;
        } else if (!email.match(/^[a-z0-9.]+@[a-z0-9]+\.[a-z]+/)) {
            mensagemErro('O email não é válido');
            return;
        }
        if(!senha){
            mensagemErro('O senha é obrigatório');
        }


        if (senha !== senhaRepeticao) {
            mensagemErro('As senhas não coincidem');
            return;
        }
        
        usuarioService.salvar(usuario)
            .then(response => {
                mensagemSucesso('Usuário cadastrado com sucesso');
                navigate('/home');
            })
            .catch(error => {
                if (error.response && error.response.data) {
                    mensagemErro(error.response.data);
                } else {
                    mensagemErro('Erro desconhecido');
                }
            });
    };

    return (
        <div className="container">
            <Card title="Cadastro de Usuario">
                <div className="row">
                    <div className="col-lg-12">
                        <div className="bs-component">
                            <FormGroup label="Nome: *" htmlFor="inputNome">
                                <input type="text" name="nome"
                                    id="inputNome" placeholder="Nome" onChange={e => setNome(e.target.value)}
                                    className="form-control" />
                            </FormGroup>
                            <FormGroup label="Email: *" htmlFor="inputEmail">
                                <input type="text" name="email"
                                    id="inputEmail" placeholder="Email" onChange={e => setEmail(e.target.value)}
                                    className="form-control" />
                            </FormGroup>
                            <FormGroup label="Senha: *" htmlFor="inputSenha">
                                <input type="password" name="senha"
                                    id="inputSenha" placeholder="Senha" onChange={e => setSenha(e.target.value)}
                                    className="form-control" />
                            </FormGroup>
                            <FormGroup label="Confirmação de Senha: *" htmlFor="inputSenhaRepetir">
                                <input type="password" name="senhaRepetir"
                                    id="inputSenhaRepetir" placeholder="Confirmação da senha" onChange={e => setSenhaRepeticao(e.target.value)}
                                    className="form-control" />
                            </FormGroup>
                            <button onClick={cadastrar} type="button" className="btn btn-success">Salvar</button>
                            <button onClick={() => navigate('/home')} type="button" className="btn btn-danger">Cancelar</button>
                        </div>
                    </div>
                </div>
            </Card>
        </div>
    );
};

export default CadastroUsuario;
