package pl.marcisz.patryk.demo.spring131.exception;

import java.time.LocalDateTime;

public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(addTimeToMessage(message));
    }

    static String addTimeToMessage(String message){
        return String.format("%s:%s", LocalDateTime.now(), message);
    }

}
