package com.example.rest.controller;

import com.example.rest.entity.Base;
import com.example.rest.exception.BaseNotFoundException;
import com.example.rest.repository.BaseRepository;
import org.springframework.hateoas.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/rest")
public class BaseController {

    private BaseRepository baseRepository;


    @Autowired
    public BaseController(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    //Get the Base list
    @GetMapping("/list")
    public List<Base> getList() {
        return baseRepository.findAll();
    }

    //Find one of the Base list
    @GetMapping("/list/{id}")
    public Resource<Base> show(@PathVariable long id) {
        Optional<Base> optionalBase = Optional.ofNullable(baseRepository.findOne(id));

        if (!optionalBase.isPresent()) {
            throw new BaseNotFoundException("id: " + id);
        } else {
            Resource<Base> resource = new Resource<>(optionalBase.get());
            ControllerLinkBuilder builder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getList());
            resource.add(builder.withRel("Base list"));
            return resource;
        }
    }

    //Add new file to Base
    @PostMapping("/list")
    public ResponseEntity addBase(@RequestBody Base base) {
        baseRepository.save(base);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(base.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Find files that contains searches characters
    @PostMapping("list/search")
    public List<Base> search(@RequestBody String contain) {
        return baseRepository.findByNameContaining(contain);
    }

    //Delete file
    @DeleteMapping("list/{id}")
    public ResponseEntity deleteBase(@PathVariable long id) {
        Optional<Base> optionalBase = Optional.ofNullable(baseRepository.findOne(id));

        if (!optionalBase.isPresent()) {
            throw new BaseNotFoundException("id: " + id);
        } else {
            baseRepository.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

    //Update file
    @PutMapping("list/{id}")
    public ResponseEntity updateBase(@PathVariable long id, @RequestBody @Valid Base base) {
        Optional<Base> optionalBase = Optional.ofNullable(baseRepository.findOne(id));

        if (!optionalBase.isPresent()) {
            throw new BaseNotFoundException("id: " + id);
        } else {
            base.setId(id);
            baseRepository.save(base);
            return ResponseEntity.noContent().build();
        }
    }
}
