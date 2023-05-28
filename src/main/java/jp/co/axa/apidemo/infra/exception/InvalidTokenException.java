package jp.co.axa.apidemo.infra.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }
}
