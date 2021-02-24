package com.champion.academy.regististion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.parser.Entity;

@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErroMessage> handleUserNotFoundException(Exception e) {
        ErroMessage message = new ErroMessage();
        message.setErroCode("404");
        message.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AdequateInputNotFound.class)
    public ResponseEntity<ErroMessage> inputDataNotSufficient(Exception e) {
        ErroMessage message = new ErroMessage();
        message.setErroCode("555");
        message.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
