package com.ahmetburak.article.exception;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
