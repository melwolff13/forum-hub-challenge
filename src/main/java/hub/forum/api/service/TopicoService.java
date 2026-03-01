package hub.forum.api.service;

import hub.forum.api.domain.topico.DadosDetalhamentoTopico;
import hub.forum.api.domain.topico.DadosNovoTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.topico.TopicoRepository;
import hub.forum.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoTopico criarTopico(@Valid DadosNovoTopico dados) {
        var autor = usuarioRepository.findById(dados.idAutor());
        if (autor.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado");
        }
        var topico = new Topico(dados, autor.get());
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public Page<DadosDetalhamentoTopico> listarTodosTopicos(Pageable paginacao) {
        return topicoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoTopico::new);
    }

    public DadosDetalhamentoTopico detalharTopico(Long id) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id);
        if (topico.isEmpty()) {
            throw new RuntimeException("Tópico não encontrado");
        }
        return new DadosDetalhamentoTopico(topico.get());
    }
}
