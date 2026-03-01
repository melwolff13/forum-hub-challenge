package hub.forum.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosNovoTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        LocalDateTime dataCriacao,
        @NotNull
        Long idAutor,
        @NotNull
        String curso) {
}
