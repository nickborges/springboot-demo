package br.com.springboot.demo.domain;

public class BuscarPessoaResponse {

    private Long id;
    private String nome;
    private Integer idade;

    public BuscarPessoaResponse() {}
    public BuscarPessoaResponse(Long id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}