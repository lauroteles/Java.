package lauro.project.minhasfinancas.service;

import lauro.project.minhasfinancas.entity.Usuario;

import java.math.BigDecimal;
import java.util.Optional;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

    Optional<Usuario> obeterPorId(Long id);


}
