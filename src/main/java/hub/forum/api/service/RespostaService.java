package hub.forum.api.service;

import hub.forum.api.domain.resposta.*;
import hub.forum.api.domain.topico.TopicoRepository;
import hub.forum.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public DadosDetalhamentoResposta responderTopico(@Valid DadosResposta dados) {
        var topico = topicoRepository.getReferenceById(dados.idTopico());
        var autor = usuarioRepository.getReferenceById(dados.idAutor());

        var resposta = new Resposta(dados, topico, autor);
        topico.responder(resposta);
        respostaRepository.save(resposta);

        return new DadosDetalhamentoResposta(resposta);
    }

    public Page<DadosDetalhamentoResposta> respostasPorTopico(Long id, Pageable paginacao) {
        return respostaRepository.findAllByTopicoId(id, paginacao).map(DadosDetalhamentoResposta::new);
    }

    @Transactional
    public DadosDetalhamentoResposta atualizarResposta(Long id, DadosAtualizacaoResposta dados) {
        var resposta = respostaRepository.getReferenceById(id);
        resposta.atualizarInformacoes(dados);

        return new DadosDetalhamentoResposta(resposta);
    }

    @Transactional
    public void deletarResposta(Long id) {
        var resposta = respostaRepository.getReferenceById(id);
        resposta.deletar();
    }
}
