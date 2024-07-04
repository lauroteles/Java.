package lauro.project.minhasfinancas.service.impl;

import lauro.project.minhasfinancas.entity.Usuario;
import lauro.project.minhasfinancas.model.repository.UsuarioRepository;
import lauro.project.minhasfinancas.service.UsuarioService;
import lauro.project.minhasfinancas.service.exception.ErroAutenticacao;
import lauro.project.minhasfinancas.service.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UsuarioServiceImpl  implements UsuarioService {


    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);

        if (!usuario.isPresent()) {
            throw  new ErroAutenticacao("Usuario não encontrado!");
        }
        if (!usuario.get().getSenha().equals(senha)) {
                throw new ErroAutenticacao("Senha incorreta!");
        }

        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Esse e-mail ja esta cadastrado.");

        }

    }

    @Override
    public Optional<Usuario> obeterPorId(Long id) {
        return repository.findById(id);
    }
}
