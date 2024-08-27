package com.rodrigomoreira.todo.exceptions;

import com.rodrigomoreira.todo.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> threat404(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

}
