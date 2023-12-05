package top.expli.schoolfish;

import top.expli.schoolfish.exceptions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(TooManyDataRequested.class)
    @ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
    public ResponseEntity<ExceptionResponse> tooManyDataRequestedHandler(TooManyDataRequested ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }

    public static class ExceptionResponse {
        public String message;

        public ExceptionResponse(String message) {
            this.message = message;
        }
    }
}