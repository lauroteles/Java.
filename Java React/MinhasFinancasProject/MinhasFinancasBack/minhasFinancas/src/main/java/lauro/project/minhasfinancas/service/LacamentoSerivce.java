package lauro.project.minhasfinancas.service;


import lauro.project.minhasfinancas.entity.Lancamento;
import lauro.project.minhasfinancas.model.enums.StatusLancamento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LacamentoSerivce {

    Lancamento salvar(Lancamento lancamento);
    Lancamento atualizar(Lancamento lancamento);
    void deletar(Lancamento lancamento);
    List<Lancamento> buscar(Lancamento lancamentoFiltro);
    void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);
    void validar(Lancamento lancamento);
    Optional<Lancamento> obterPorId(Long id);
    BigDecimal obterSaldoPorUsuario(Long id);

}
