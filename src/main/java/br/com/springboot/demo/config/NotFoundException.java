package br.com.springboot.demo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private String error;
    private String message;

}
