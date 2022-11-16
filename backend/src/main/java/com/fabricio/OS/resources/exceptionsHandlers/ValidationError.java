package com.fabricio.OS.resources.exceptionsHandlers;

public class ValidationError extends StandardError{
    
    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }
    
    public ValidationError(){

    }

}
