package com.fabricio.OS.resources.exceptionsHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fabricio.OS.services.exceptions.DataIntegratyViolationException;
import com.fabricio.OS.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());

        
        //Retornará um 404, com o corpo do objeto instanciado error
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

   @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException e){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());

        
        //Retornará um Bad Request
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(javax.validation.ConstraintViolationException e){
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos!");

        //Retornará um Bad Request com uma lista com todos as validações que não foram atendidas
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

  
}
