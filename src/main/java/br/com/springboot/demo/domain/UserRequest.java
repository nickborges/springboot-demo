package br.com.springboot.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
@EqualsAndHashCode
public class UserRequest {

    private String name;
    private int age;

}
