package br.com.springboot.demo.config.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@AllArgsConstructor
@NotEmpty
public class FieldException {

    private String field;
    private String message;

    public FieldException(FieldError fieldError, String msg){
        this.field = fieldError.getField();
        this.message = msg;
    }

}
