package hub.forum.api.infra.exceptions;

public class ValidacaoDoAutorException extends RuntimeException {
    public ValidacaoDoAutorException(String mensagem) {
        super(mensagem);
    }
}
