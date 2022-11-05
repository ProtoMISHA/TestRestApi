package com.example.test.excepiton;

public class NoSuchBookException extends  RuntimeException{

    public NoSuchBookException(String message){
        super(message);
    }


}
