package br.com.springboot.demo.controller;

import br.com.springboot.demo.domain.UserRequest;
import br.com.springboot.demo.domain.UserResponse;
import br.com.springboot.demo.service.SpringbootDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class SpringbootDemoController {

    @Autowired
    SpringbootDemoService service;

    @GetMapping("/all")
    public ResponseEntity execute(){
        return ResponseEntity.ok(service.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity execute(@PathVariable Long id){
        return ResponseEntity.ok(service.execute(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity execute(@RequestBody @Valid UserRequest request, UriComponentsBuilder uri){
        UserResponse response = service.execute(request);

        return ResponseEntity.created(
                uri.path("/user/{id}").buildAndExpand(response.getUser().getId()).toUri()
        ).body(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity execute(@PathVariable Long id,
                                  @RequestBody @Valid UserRequest request){

        UserResponse response = service.execute(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity executed(@PathVariable Long id){
        return ResponseEntity.ok(service.executed(id));

    }

}
