package br.com.springboot.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private Long id;
    private String nome;

    public String mensagem(Long id, String nome){
        this.id = id;
        this.nome = nome;
        String response = "Response: ID = " + getId() + " Nome = " + getNome();
        System.out.println(response);
        return response;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
