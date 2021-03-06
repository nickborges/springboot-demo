package br.com.springboot.demo.controller;

import br.com.springboot.demo.domain.UserRequest;
import br.com.springboot.demo.domain.UserResponse;
import br.com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users")
    @Cacheable("users")
    public ResponseEntity<Page> execute(
            @PageableDefault(sort = "name",
                             direction = Sort.Direction.ASC,
                             page = 0,
                             size = 10) Pageable page){

        return ResponseEntity.ok(service.execute(page));
    }

    @GetMapping("/{id}")
    @Cacheable("users")
    public ResponseEntity execute(@PathVariable Long id){
        return ResponseEntity.ok(service.execute(id));
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity execute(@RequestBody @Valid UserRequest request, UriComponentsBuilder uri){
        UserResponse response = service.execute(request);

        return ResponseEntity.created(
                uri.path("/user/{id}").buildAndExpand(response.getUser().getId()).toUri()
        ).body(response);
    }

    @PutMapping("/user/{id}")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity execute(@PathVariable Long id,
                                  @RequestBody @Valid UserRequest request){

        UserResponse response = service.execute(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity executed(@PathVariable Long id){
        return ResponseEntity.ok(service.executed(id));

    }

}
