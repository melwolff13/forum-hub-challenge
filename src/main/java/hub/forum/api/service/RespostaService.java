package hub.forum.api.service;

import hub.forum.api.domain.resposta.*;
import hub.forum.api.domain.topico.TopicoRepository;
import hub.forum.api.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
        var topico = topicoRepository.findByIdAndAtivoTrue(dados.idTopico())
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + dados.idTopico()));
        var autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + dados.idAutor()));

        var resposta = new Resposta(dados, topico, autor);
        topico.responder(resposta);
        respostaRepository.save(resposta);

        return new DadosDetalhamentoResposta(resposta);
    }

    public Page<DadosDetalhamentoResposta> respostasPorTopico(Long id, Pageable paginacao) {
        return respostaRepository.findAllByTopicoIdAndAtivoTrue(id, paginacao).map(DadosDetalhamentoResposta::new);
    }

    @Transactional
    public DadosDetalhamentoResposta atualizarResposta(Long id, DadosAtualizacaoResposta dados) {
        var resposta = respostaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        resposta.atualizarInformacoes(dados);

        return new DadosDetalhamentoResposta(resposta);
    }

    @Transactional
    public void deletarResposta(Long id) {
        var resposta = respostaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado: " + id));
        resposta.deletar();
    }
}
