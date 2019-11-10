package br.com.springboot.demo.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class UserResponse {

    private User user;

    @Builder
    @Getter
    public static final class User{
        private Long id;
        private String name;
        private int age;
    }

}
