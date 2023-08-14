package org.happiestminds.springboottestingproject.exception;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message){
        super(message);
    }

    // parameterize constructor
    public ResourceNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
