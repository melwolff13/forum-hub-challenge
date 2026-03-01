package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.DadosDetalhamentoTopico;
import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        String mensagem,
        LocalDateTime dataCriacao,
        DadosDetalhamentoUsuario autor,
        DadosDetalhamentoTopico topico) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getMensagem(), resposta.getDataCriacao(), new DadosDetalhamentoUsuario(resposta.getAutor()), new DadosDetalhamentoTopico(resposta.getTopico()));
    }
}
