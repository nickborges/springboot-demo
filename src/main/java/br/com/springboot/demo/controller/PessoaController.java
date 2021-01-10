package br.com.springboot.demo.controller;

import br.com.springboot.demo.domain.BuscarPessoaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @GetMapping("/pessoa/{id}")
    public ResponseEntity get(@PathVariable Long id,
                              String nome,
                              Integer idade){

        BuscarPessoaResponse response =
                new BuscarPessoaResponse(id, nome, idade);

        System.out.println("Response buscar Pessoa: " + response.getNome() + " ID: " + response.getNome());

        return ResponseEntity.ok("Teste buscar Pessoa: " + response.getNome() + " ID: " + response.getNome());

    }

}
