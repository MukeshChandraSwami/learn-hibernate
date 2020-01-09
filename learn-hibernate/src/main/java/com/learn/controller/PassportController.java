package com.learn.controller;

import com.learn.entity.Customer;
import com.learn.entity.Passport;
import com.learn.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    PassportService passportService;

    @GetMapping("/{id}/get")
    public ResponseEntity get(@PathVariable (required = true, name = "id") long id){

        ResponseEntity entity = null;
        Optional<Passport> passportOp = passportService.get(id);
        if(passportOp.isPresent()){
            entity = new ResponseEntity(passportOp.get(), HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return entity;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Passport passport) {
        ResponseEntity entity;

        Passport savedPassport = passportService.save(passport);

        if(savedPassport != null){
            entity = new ResponseEntity("Id :- " + savedPassport.getId(), HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return entity;
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity save(@PathVariable(required = true, name = "id") long id) {
        ResponseEntity entity;

        boolean status = passportService.delete(id);

        if(status){
            entity = new ResponseEntity(HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return entity;
    }
}
