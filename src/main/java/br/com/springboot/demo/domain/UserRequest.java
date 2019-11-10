package br.com.springboot.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@ToString
@EqualsAndHashCode
public class UserRequest {

    @NotNull @NotEmpty
    private String name;

    @NotNull
    private Integer age;

}
