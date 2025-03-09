package com.example.Hong_park_springBoot.api;

import com.example.Hong_park_springBoot.dto.CoffeeForm;
import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.entity.Coffee;
import com.example.Hong_park_springBoot.repository.CoffeeRepository;
import com.example.Hong_park_springBoot.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    //get
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }
    //post
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm form){
        Coffee created = coffeeService.create(form);
        return (created == null)?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build():
                ResponseEntity.status(HttpStatus.OK).body(created);

    }
    //patch
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@RequestBody CoffeeForm dto,@PathVariable Long id){
        Coffee target = coffeeService.update(dto,id);
        return (target == null)?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build():
                ResponseEntity.status(HttpStatus.OK).body(target);

    }
    //delete
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeService.delete(id);
        return (target == null)?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

