package hub.forum.api.service;

import hub.forum.api.domain.topico.*;
import hub.forum.api.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoTopico criarTopico(@Valid DadosNovoTopico dados) {
        var autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + dados.idAutor()));
        var topico = new Topico(dados, autor);
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public Page<DadosDetalhamentoTopico> listarTodosTopicos(Pageable paginacao) {
        return topicoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoTopico::new);
    }

    public DadosDetalhamentoTopico detalharTopico(Long id) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        return new DadosDetalhamentoTopico(topico);
    }

    public DadosDetalhamentoTopico atualizarTopico(Long id, DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        topico.atualizarInformacoes(dados);

        return new DadosDetalhamentoTopico(topico);
    }

    public void deletarTopico(Long id) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        topico.deletar();
    }

}
