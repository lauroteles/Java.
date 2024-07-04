package lauro.project.minhasfinancas.api.resource;

import lauro.project.minhasfinancas.api.DTO.AtualizaStatusDTO;
import lauro.project.minhasfinancas.api.DTO.LancamentoDTO;
import lauro.project.minhasfinancas.entity.Lancamento;
import lauro.project.minhasfinancas.entity.Usuario;
import lauro.project.minhasfinancas.model.enums.StatusLancamento;
import lauro.project.minhasfinancas.model.enums.TipoLancamento;
import lauro.project.minhasfinancas.service.LacamentoSerivce;
import lauro.project.minhasfinancas.service.UsuarioService;
import lauro.project.minhasfinancas.service.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoRosource {


    private LacamentoSerivce serivce;
    private UsuarioService usuarioService;

    public LancamentoRosource(LacamentoSerivce serivce, UsuarioService usuarioService, LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        this.serivce = serivce;
        this.usuarioService = usuarioService;

    }



    @PutMapping("{id}/atualiza-status")
    public ResponseEntity atualizarStatus(@PathVariable Long id,@RequestBody AtualizaStatusDTO dto) {
            return  serivce.obterPorId(id).map(entity -> {
                StatusLancamento statusSelecionado = StatusLancamento.valueOf(dto.getStatus());
                if(statusSelecionado == null) {
                    return ResponseEntity.badRequest().body("Não foi possivel atualizar o status do lançamento.");
                }
                try {
                    entity.setStatus(statusSelecionado);
                    serivce.atualizar(entity);
                    return ResponseEntity.ok(entity);
                }catch (RegraNegocioException e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }).orElseGet(() ->
                    new ResponseEntity("Lançamento não encontrado", HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public  ResponseEntity buscar(
            @RequestParam(value = "descricao",required = false) String descricao,
            @RequestParam(value = "mes",required = false) Integer mes,
            @RequestParam(value = "ano",required = false) Integer ano,
            @RequestParam("usuario") Long idUsuario) {

        Lancamento lancamentoFiltro = new Lancamento();
        lancamentoFiltro.setDescricao(descricao);
        lancamentoFiltro.setMes(mes);
        lancamentoFiltro.setAno(ano);

       Optional<Usuario> usuario = usuarioService.obeterPorId(idUsuario);
       if (!usuario.isPresent()) {
           return ResponseEntity.badRequest().body("Não foi possível realizar a busca");
       }else {
           lancamentoFiltro.setUsuario(usuario.get());
       }
        List<Lancamento> lancamentos = serivce.buscar(lancamentoFiltro);
       return ResponseEntity.ok(lancamentos);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {
        try {
            Lancamento entidade = converter(dto);
            serivce.salvar(entidade);
            entidade = serivce.salvar(entidade);
            return  new ResponseEntity(entidade, HttpStatus.CREATED);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return  serivce.obterPorId(id).map(entidade -> {
            serivce.deletar(entidade);
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet( () ->
        new ResponseEntity("Lancamento não encontrado",HttpStatus.BAD_REQUEST));
    }


    @PutMapping("{id}")
    public ResponseEntity atualizar (@PathVariable("id") Long id , @RequestBody LancamentoDTO dto) {

            return serivce.obterPorId(id).map(entity -> {
                try {
                Lancamento lancamento = converter(dto);
                lancamento.setId(entity.getId());
                serivce.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);
            }catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() ->
                new ResponseEntity<>("Lancamento nao encontrado",HttpStatus.BAD_REQUEST));

    }

    private Lancamento converter(LancamentoDTO dto) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());
        lancamento.setValor(dto.getValor());

         Usuario usuario = usuarioService
                .obeterPorId(dto.getUsuario())
                .orElseThrow (() -> new RegraNegocioException("Usuário não encontrado!"));


        lancamento.setUsuario(usuario);

        if (dto.getTipo() != null) {
            lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
        }
        if(dto.getStatus() != null) {
            lancamento.setStatus(StatusLancamento.valueOf(String.valueOf(dto.getStatus())));
        }
        return lancamento;
    }
}
