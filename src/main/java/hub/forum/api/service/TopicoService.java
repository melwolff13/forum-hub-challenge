package hub.forum.api.service;

import hub.forum.api.domain.topico.*;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.domain.usuario.UsuarioRepository;
import hub.forum.api.infra.exceptions.ValidacaoDoAutorException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoTopico criarTopico(@Valid DadosNovoTopico dados, Usuario usuarioLogado) {
        var autor = usuarioRepository.findById(usuarioLogado.getId())
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + usuarioLogado.getId()));
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

    public DadosDetalhamentoTopico atualizarTopico(Long id, DadosAtualizacaoTopico dados, Usuario usuarioLogado) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        validarAutor(topico, usuarioLogado);

        topico.atualizarInformacoes(dados);

        return new DadosDetalhamentoTopico(topico);
    }

    public void deletarTopico(Long id, Usuario usuarioLogado) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        validarAutor(topico, usuarioLogado);
        topico.deletar();
    }

    private void validarAutor(Topico topico, Usuario usuarioLogado) {
        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new ValidacaoDoAutorException("Você não tem permissão para essa ação");
        }
    }

}
