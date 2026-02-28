package hub.forum.api.domain.topico;

import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        DadosDetalhamentoUsuario autor,
        String curso) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), new DadosDetalhamentoUsuario(topico.getAutor()), topico.getCurso());
    }
}
