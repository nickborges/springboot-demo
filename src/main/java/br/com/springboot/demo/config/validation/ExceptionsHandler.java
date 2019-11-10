package br.com.springboot.demo.config.validation;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handle(NotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(MethodArgumentNotValidException exception){
        List<FieldException> fields = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(f -> {
            fields.add(
                    FieldException.builder()
                            .field(f.getField())
                            .message(messageSource.getMessage(f, LocaleContextHolder.getLocale()))
                            .build()
            );
        });

        return ResponseEntity.badRequest().body(fields);
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
