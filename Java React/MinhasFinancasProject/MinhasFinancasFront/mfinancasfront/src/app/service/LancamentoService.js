import ApiService from "../apiservice";

export default class lancamentoService extends ApiService {

    constructor() {
        super('/api/lancamentos')
    }
    obterListaMeses(){
        return  [
            { label: 'Selecione...', value: '' },
            { label: 'Janeiro', value: 1 },
            { label: 'Fevereiro', value: 2 },
            { label: 'Março', value: 3 },
            { label: 'Abril', value: 4 },
            { label: 'Maio', value: 5 },
            { label: 'Junho', value: 6 },
            { label: 'Julho', value: 7 },
            { label: 'Agosto', value: 8 },
            { label: 'Setembro', value: 9 },
            { label: 'Outubro', value: 10 },
            { label: 'Novembro', value: 11 },
            { label: 'Dezembro', value: 12 },
        ]
    }

    obterListaTipos(){
        return  [
            { label: 'Selecione...', value: '' },
            { label: 'Despesa' , value : 'DESPESA' },
            { label: 'Receita' , value : 'RECEITA' }
        ]

    }

    salvar(lancamento, userId) {
        return this.post(`/${userId}`, lancamento);
    }

    obterPorId(id){
        return this.get(`/${id}`);
    }

    alterarStatus(id, status){
        return this.put(`/${id}/atualiza-status`, { status })
    }

    validar(lancamento){
        const erros = []};

    consultar(LancamentoFiltro){
        let params = `?ano=${LancamentoFiltro.ano}&mes=${LancamentoFiltro.mes}
        &descricao=${LancamentoFiltro.descricao}&tipo=${LancamentoFiltro.tipo}`

        if (LancamentoFiltro.mes){
            params = `${params}&mes${LancamentoFiltro.mes}`
        }
        if(LancamentoFiltro.tipo){
            params = `${params}&tipo=${LancamentoFiltro.tipo}`
        }
        if (LancamentoFiltro.status){
            params = `${params}&status=${LancamentoFiltro.status}`
        }
        if (LancamentoFiltro.usuario) {
            params = `${params}&usuario=${LancamentoFiltro.usuario}`
        }

        if(LancamentoFiltro.descricao) {
            params = `${params}&descricao=${LancamentoFiltro.descricao}`
        }
        
        return this.get(params)

    }
    deletar(id) {
        return this.delete(`/${id}`)
    }
}