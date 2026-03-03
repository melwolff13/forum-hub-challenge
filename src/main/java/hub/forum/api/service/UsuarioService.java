package hub.forum.api.service;

import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;
import hub.forum.api.domain.usuario.DadosNovoUsuario;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DadosDetalhamentoUsuario cadastrarUsuario(DadosNovoUsuario dados) {
        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var novoUsuario = new Usuario(dados, senhaCriptografada);
        usuarioRepository.save(novoUsuario);
        return new DadosDetalhamentoUsuario(novoUsuario);
    }
}
