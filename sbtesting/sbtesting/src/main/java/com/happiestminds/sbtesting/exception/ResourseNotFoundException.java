package com.happiestminds.sbtesting.exception;


public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String message){
        super(message);
    }

    public ResourseNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
