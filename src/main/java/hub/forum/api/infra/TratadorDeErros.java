package hub.forum.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(EntityNotFoundException ex) {
        var erro = ex.getMessage();
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErro::new));
    }

    private record DadosErro(String campo, String descricao) {
        public DadosErro(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
