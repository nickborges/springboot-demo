package br.com.springboot.demo.config.validation;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Message{
    private String error;
    private String message;
}