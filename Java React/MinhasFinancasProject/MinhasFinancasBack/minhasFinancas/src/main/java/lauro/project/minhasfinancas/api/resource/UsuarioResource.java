package lauro.project.minhasfinancas.api.resource;



import jdk.javadoc.doclet.Reporter;
import lauro.project.minhasfinancas.api.DTO.UsuarioDTO;
import lauro.project.minhasfinancas.entity.Usuario;
import lauro.project.minhasfinancas.service.LacamentoSerivce;
import lauro.project.minhasfinancas.service.UsuarioService;
import lauro.project.minhasfinancas.service.exception.ErroAutenticacao;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    @Autowired
    private final UsuarioService service;
    @Autowired
    private final LacamentoSerivce lacamentoSerivce;

    public UsuarioResource(UsuarioService service, LacamentoSerivce lacamentoSerivce) {
        this.service = service;
        this.lacamentoSerivce = lacamentoSerivce;
    }


    @PostMapping("/autenticar")
    public  ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (

                ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();

        try {
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping ("{id}/saldo")
    public ResponseEntity obterSaldo(@PathVariable("id") Long id) {

        Optional<Usuario> usuario = service.obeterPorId(id);

        if (!usuario.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        BigDecimal saldo = lacamentoSerivce.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }

}
