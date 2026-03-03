package hub.forum.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosNovoUsuario(
        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha) {
}
