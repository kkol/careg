package kkol.crc.careg.config;

import kkol.crc.careg.model.Car;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


public interface GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    default  Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("statusCode", String.valueOf(exception.getStatusCode().value()));

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    default Map<String, Object> handleValidationExceptions(DataIntegrityViolationException exception) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("statusCode", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorResponse.put("error", exception.getMessage());
        return errorResponse;
    }
}
