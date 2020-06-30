package br.com.springboot.demo.domain;

public class AlterarPessoaRequest {

    public String nome;
    public Integer idade;

    public AlterarPessoaRequest(){

    }

    public AlterarPessoaRequest(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

}
