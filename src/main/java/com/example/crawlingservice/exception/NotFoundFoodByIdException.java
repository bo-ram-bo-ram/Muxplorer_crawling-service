package com.example.crawlingservice.exception;

public class NotFoundFoodByIdException extends RuntimeException{

    public NotFoundFoodByIdException(String message) {
        super(message);
    }
}
