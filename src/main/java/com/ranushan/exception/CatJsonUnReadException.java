package com.ranushan.exception;

public class CatJsonUnReadException extends CatException {
    public CatJsonUnReadException(String message) { // If you unread json file then throw this exception
        super(message);
    }
}
