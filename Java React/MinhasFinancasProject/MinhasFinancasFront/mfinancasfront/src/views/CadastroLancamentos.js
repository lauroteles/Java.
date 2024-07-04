import React from 'react';
import Card from "../components/card";
import FormGroup from "../components/Form-goup";
import SelectMenu from "../components/SelectMenu";
import lancamentoService from "../app/service/LancamentoService";
import localStorageServ from '../app/service/localStorageService';

class CadastrarLancamentos extends React.Component {
  constructor() {
    super();
    this.state = {
      meses: [],
      tipos: [],
    };
    this.service = new lancamentoService();
  }
  submit = () => {
    const usuarioLogado = localStorageServ.obterItem('_usuario_logado')
    const lancamento = {
        descricao: this.state.descricao,
        valor: this.state.valor,
        tipo: this.state.tipo,
        mes: this.state.mes,
        ano: this.state.ano,
        status: this.state.status
    };

    this.service.salvar(lancamento, usuarioLogado.id).then(response => {
        console.log('Lançamento salvo com sucesso!', response);
    }).catch(error => {
        console.log('Erro ao salvar lançamento', error);
    });
}

    state = {
        id: null,
        descricao: "",
        valor: "",
        tipo: "",
        mes: "",
        ano: "",
        status: ""
    }

  componentDidMount() {
    const meses = this.service.obterListaMeses();
    const tipos = this.service.obterListaTipos();
    this.setState({ meses, tipos });
  }

  handleChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;

    this.setState({ [name] : value })
  }


  render() {
    return (
      <Card>
        <div className="row">
          <div className="col-md-6">
            <FormGroup id='InputDescricao' label="Descricao: *">
              <input type="text" className="form-control" 
              id="InputDescricao" placeholder="Descrição" 
              name='descricao' onChange={this.handleChange} value={this.state.descricao}/>
            </FormGroup>
          </div>

          <div className="col-md-6">
            <FormGroup id='InputAno' label="Ano: *">
              <input type="text" className="form-control" id="InputAno" placeholder="Ano" 
                name='ano' onChange={this.handleChange} value={this.state.ano}/>
            </FormGroup>
          </div>

          <div className="col-md-6">
            <FormGroup id='InputDescricao' label="Mês: *">
              <SelectMenu lista={this.state.meses} type="text" className="form-control" id="InputDescricao" placeholder="Mês" 
                            name='mes' onChange={this.handleChange} value={this.state.mes}/>
            </FormGroup>
          </div>

          <div className="col-md-6">
            <FormGroup id='InputTipo' label="Tipo: *">
              <SelectMenu lista={this.state.tipos} type="text" className="form-control" id="InputTipo" placeholder="Tipo" 
              name='tipo' onChange={this.handleChange} value={this.state.tipo}/>
            </FormGroup>
          </div>

          <div className="col-md-6">
            <FormGroup id='InputAno' label="Valor: *">
              <input type="text" className="form-control" id="InputValor" placeholder="Valor R$" 
                name='valor' onChange={this.handleChange} value={this.state.valor}/>
            </FormGroup>
          </div>

          <div className="col-md-6">
            <FormGroup id='InputStatus' label="Status: *">
              <input type="text" className="form-control" id="InputStatus" placeholder="Status" 
                name='status' onChange={this.handleChange} value={this.state.status}/>
            </FormGroup>
          </div>

            <div>
                <button type="button" className="btn btn-primary" onClick={this.submit}>Salvar</button>
                <button type="button" className='btn btn-warning'>Cancelar</button>
            </div>

        </div>
      </Card>
    );
  }
}

export default CadastrarLancamentos;
