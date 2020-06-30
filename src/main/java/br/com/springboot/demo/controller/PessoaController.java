package br.com.springboot.demo.controller;

import br.com.springboot.demo.domain.BuscarPessoaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    BuscarPessoaResponse response;

    @GetMapping("/pessoa/{id}")
    public ResponseEntity get(@PathVariable Long id,
                              String nomeParam,
                              Integer idadeParam){
        response = new BuscarPessoaResponse();
        response.setId(id);
        response.setNome(nomeParam);
        response.setIdade(idadeParam);

        System.out.println("Response buscar Pessoa: " + response.getNome() + " ID: " + response.getId());

        return ResponseEntity.ok("Teste buscar Pessoa: " + response.getNome() + " ID: " + response.getId());
    }

}
