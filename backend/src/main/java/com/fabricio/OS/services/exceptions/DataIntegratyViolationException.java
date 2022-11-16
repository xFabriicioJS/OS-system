package com.fabricio.OS.services.exceptions;


public class DataIntegratyViolationException extends RuntimeException{

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegratyViolationException(String message) {
        super(message);
    }
    
    
}
