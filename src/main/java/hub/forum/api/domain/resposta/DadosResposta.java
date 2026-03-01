package hub.forum.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosResposta(
        @NotNull
        Long idTopico,
        @NotBlank
        String mensagem,
        @NotNull
        LocalDateTime dataCriacao,
        @NotNull
        Long idAutor) {
}
