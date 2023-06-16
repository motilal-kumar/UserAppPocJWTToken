package com.neosoft.userapppocjwttoken.exception;

import com.neosoft.userapppocjwttoken.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * ValidationHandler.
 *
 * @author Motilal  Kumar.
 *  version 1.0
 *
 */
@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    /**
     * handleMethodArgumentNotValid.
     *
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return
     *
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * ExceptionResponse.
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ExceptionResponse globalExceptionHandler(Exception exception, WebRequest request) {
        ExceptionResponse error = new ExceptionResponse();
       // error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
       // error.setTimestamp(new Date());
        error.setMessage(exception.getMessage());
      //  error.setDescription(request.getDescription(false));
        return error;
    }

}
