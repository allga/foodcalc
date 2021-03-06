package com.outdoor.foodcalc;

import com.outdoor.foodcalc.model.ErrorMessage;
import com.outdoor.foodcalc.model.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Spring advise, that contains all exceptions handlers
 *
 * @author Anton Borovyk.
 */
@ControllerAdvice
public class ExceptionHandlers {

    String getErrorMessage(Exception e) {
        String message = e.getMessage();
        if (message == null) {
            message = e.getClass().getSimpleName();
        }
        return message;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, ValidationException.class})
    public ResponseEntity<ErrorMessage> validationException(final RuntimeException e) {
        return new ResponseEntity<>(new ErrorMessage(getErrorMessage(e)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeException(final RuntimeException e) {
        return new ResponseEntity<>(new ErrorMessage(getErrorMessage(e)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
