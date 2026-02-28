package hub.forum.api.domain.topico;

import java.time.LocalDateTime;

public record DadosNovoTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Long idAutor,
        String curso) {
}
