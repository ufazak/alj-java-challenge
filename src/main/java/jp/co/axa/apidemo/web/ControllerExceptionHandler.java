package jp.co.axa.apidemo.web;

import jp.co.axa.apidemo.infra.exception.InvalidTokenException;
import jp.co.axa.apidemo.infra.exception.UserNotFoundException;
import jp.co.axa.apidemo.web.dto.ErrorBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            BadCredentialsException.class,
            InvalidTokenException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<?> handle(Exception exception) {
        return status(BAD_REQUEST)
                .body(new ErrorBody(exception.getMessage()));
    }
}
