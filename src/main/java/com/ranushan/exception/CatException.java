package com.ranushan.exception;

public class CatException extends RuntimeException { // Own Exception => All exception should be inherited this class

    public CatException(String message) {
        super(message);
    }
}
