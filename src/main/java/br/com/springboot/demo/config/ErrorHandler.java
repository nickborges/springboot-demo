package br.com.springboot.demo.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @Autowired
    private MessageSource source;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handle(NotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity hadle(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Message.builder()
                        .error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                        .message(exception.getCause().toString())
                        .build());

    }

    @Builder
    @Getter
    static final class Message{
        private String error;
        private String message;
    }
}
