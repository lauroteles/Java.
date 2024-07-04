package lauro.project.minhasfinancas.service.impl;


import lauro.project.minhasfinancas.entity.Lancamento;
import lauro.project.minhasfinancas.model.enums.StatusLancamento;
import lauro.project.minhasfinancas.model.enums.TipoLancamento;
import lauro.project.minhasfinancas.model.repository.LancamentoRepository;
import lauro.project.minhasfinancas.service.LacamentoSerivce;
import lauro.project.minhasfinancas.service.exception.RegraNegocioException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class LancamentoServiceImpl implements LacamentoSerivce {


    private LancamentoRepository repository;

    public  LancamentoServiceImpl(LancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        validar(lancamento);
        Objects.requireNonNull(lancamento.getId());
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Override
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
        lancamento.setStatus(statusLancamento);
        atualizar(lancamento);

    }

    @Override
    public void validar(Lancamento lancamento) {
        if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Informe uma descrição valida");
        }
        if (lancamento.getMes()==null||lancamento.getMes()<1||lancamento.getMes()>12) {
            throw new RegraNegocioException(("Informe um mês valido"));
        }
        if(lancamento.getAno()==null || lancamento.getAno().toString().length() !=4) {
            throw new RegraNegocioException("informe um Ano válido");
        }
        if(lancamento.getUsuario()==null||lancamento.getUsuario().getId()==null) {
            throw new RegraNegocioException("Informe um Usuário");
        }
        if (lancamento.getValor()==null || lancamento.getValor().compareTo( BigDecimal.ZERO)< 1) {
            throw new RegraNegocioException("informe um Valor Válido");
        }

    }

    @Override
    public Optional<Lancamento> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal obterSaldoPorUsuario(Long id) {
       BigDecimal receitas = repository.obterSaldoPorTipoLancamentUsuario(id, TipoLancamento.RECEITA);
       BigDecimal despesas = repository.obterSaldoPorTipoLancamentUsuario(id,TipoLancamento.DESPESA);

       if (receitas==null) {
           receitas = BigDecimal.ZERO;
       }
       if(despesas == null) {
           despesas = BigDecimal.ZERO;
       }

        return receitas.subtract(despesas);
    }
}
