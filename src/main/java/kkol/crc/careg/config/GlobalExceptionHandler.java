package kkol.crc.careg.config;

import kkol.crc.careg.model.Car;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/*@ControllerAdvice*/
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

/*    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(exception.getStatusCode().value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }*/
}
