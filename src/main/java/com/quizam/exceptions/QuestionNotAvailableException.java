package com.quizam.exceptions;

public class QuestionNotAvailableException extends Exception {

    private String message;

    public QuestionNotAvailableException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
