import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { mensagemErro,mensagemAlerta,mensagemSucesso} from '../components/toastr'
import { Dialog } from 'primereact/dialog';

import Card from '../components/card';
import FormGroup from '../components/Form-goup';
import SelectMenu from '../components/SelectMenu';
import { LancamentosTable } from './LancamentosTable';
import lancamentoService from '../app/service/LancamentoService';
import localStorageServ from '../app/service/localStorageService';


const ConsultaLancamentos = () => {
    const [state, setState] = useState({ mes: '',
         ano: '',
          tipo: '',descricao: '',
           showConfirmDialog:false,
           lancamentoDeletar: {},       
           lancamentos: [] });
   
          const [visible,setVisible] = useState(false);
    const service = new lancamentoService();
    const navigate = useNavigate();

    const buscar = () => {

        if(!state.ano){
            mensagemErro('O preenchimento do campo Ano e obrigatorio')
            return false;
        }
        const usuarioLogado = localStorageServ.obterItem('_usuario_logado');

        const lancamentoFiltro = {
            ano: state.ano,
            mes: state.mes,
            tipo: state.tipo,
            descricao: state.descricao,
            usuario: usuarioLogado.id
        };

        service.consultar(lancamentoFiltro)
            .then(resposta => {
                setState(prevState => ({ ...prevState, lancamentos: resposta.data }));
            })
            .catch(error => {
                console.log(error);
            });
    };

    const editar = (id) => {
        console.log(id)
    }

    const deletar = (id) => {
        service.deletar(id).then(response => {
            const lancamentosAtualizados = state.lancamentos.filter(l => l.id !== id);
            setState(prevState => ({ ...prevState, lancamentos: lancamentosAtualizados }));
            mensagemSucesso('Lançamento excluído com sucesso');
        }).catch(error => {
            console.error('Erro ao deletar lançamento:', error);
            mensagemErro('Erro ao deletar lançamento');
        });
    };
    const abriConfirmacao = (lancamentos) => {
        setState({showConfirmDialog: true })
    }

    const lista = [
        { label: 'Selecione', value: '' },
        { label: 'Janeiro', value: '1' },
        { label: 'Fevereiro', value: '2' },
        { label: 'Março', value: '3' },
        { label: 'Abril', value: '4' },
        { label: 'Maio', value: '5' },
        { label: 'Junho', value: '6' },
        { label: 'Julho', value: '7' },
        { label: 'Agosto', value: '8' },
        { label: 'Setembro', value: '9' },
        { label: 'Outubro', value: '10' },
        { label: 'Novembro', value: '11' },
        { label: 'Dezembro', value: '12' }
    ];

    const tipos = [
        { label: 'Selecione', value: '' },
        { label: 'Despesa', value: 'DESPESA' },
        { label: 'Receita', value: 'RECEITA' }
    ];

    return (
        <div className="container">
            <Card title="Consulta Lançamentos">
                <div className="row">
                    <div className="col-lg-3 margin-tb">
                        <div className='bs-component'>
                            <FormGroup htmlFor="inputAno" label='Ano: *'>
                                <input
                                    type='text'
                                    className='form-control'
                                    id='inputAno'
                                    onChange={e => setState(prevState => ({ ...prevState, ano: e.target.value }))}
                                    aria-describedby='emailHelp'
                                    placeholder='Selecione o Ano'
                                    value={state.ano}
                                />
                            </FormGroup>

                            <FormGroup label='Mês: '>
                                <SelectMenu
                                    key="mes"
                                    lista={lista}
                                    className='form-control'
                                    value={state.mes}
                                    onChange={e => setState(prevState => ({ ...prevState, mes: e.target.value }))}
                                />
                            </FormGroup>

                            <FormGroup label='Tipo: '>
                                <SelectMenu
                                    key="tipo"
                                    lista={tipos}
                                    className='form-control'
                                    value={state.tipo}
                                    onChange={e => setState(prevState => ({ ...prevState, tipo: e.target.value }))}
                                />
                            </FormGroup>

                            <FormGroup htmlFor="inputDescrição" label='Descrição: '>
                                <input
                                    type='text'
                                    className='form-control'
                                    id='inputdescricao'
                                    onChange={e => setState(prevState => ({ ...prevState, descricao: e.target.value }))}
                                    aria-describedby='emailHelp'
                                    placeholder='Descrição'
                                    value={state.descricao}
                                />
                            </FormGroup>

                            <button type='button' className='btn btn-success' onClick={buscar}>Buscar</button>
                            <button type='button' className='btn btn-warning' onClick={() => navigate("/cadastro-lancamento")}>Cadastrar</button>
                        </div>
                    </div>
                </div>
                <br />
                <div className='row'>
                    <div className='col-lg-12 margin-tb'>
                        <div className='bs-component'>
                            <LancamentosTable 

                            lancamentos={state.lancamentos} 
                            deletarAciton={deletar}
                            editarAction={editar}

                            />
                        </div>
                    </div>
                </div>
                <div>
                <Dialog header="Header" 
                visible={visible} 
                style={{ width: '50vw' }} 
                onHide={() => {if (!visible) 
                return; setVisible(false); }}>


                <p className="m-0">
                                        
                </p>
                </Dialog>

                </div>
            </Card>
        </div>
    );
};

export default ConsultaLancamentos;
