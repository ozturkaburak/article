package com.ahmetburak.article.exception;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}
