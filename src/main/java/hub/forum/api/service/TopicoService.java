package hub.forum.api.service;

import hub.forum.api.domain.topico.DadosDetalhamentoTopico;
import hub.forum.api.domain.topico.DadosNovoTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.topico.TopicoRepository;
import hub.forum.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> criarTopico(@Valid DadosNovoTopico dados) {
        var autor = usuarioRepository.findById(dados.idAutor());
        if (autor.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado");
        }
        var topico = new Topico(dados, autor.get());
        topicoRepository.save(topico);

        var dto = new DadosDetalhamentoTopico(topico);
        return ResponseEntity.ok(dto);
    }
}
