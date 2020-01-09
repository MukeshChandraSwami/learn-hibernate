package com.learn.controller;

import com.learn.entity.Customer;
import com.learn.service.CustomerService;
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
@RequestMapping("/consumer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}/get")
    public ResponseEntity get(@PathVariable (required = true, name = "id") long id){

        ResponseEntity entity = null;
        Optional<Customer> customerOp = customerService.getById(id);
        if(customerOp.isPresent()){
            entity = new ResponseEntity(customerOp.get(), HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return entity;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Customer customer) {
        ResponseEntity entity;

        Customer savedCustomer = customerService.save(customer);

        if(savedCustomer != null){
            entity = new ResponseEntity("Id :- " + savedCustomer.getId(),HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return entity;
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity save(@PathVariable (required = true, name = "id") long id) {
        ResponseEntity entity;

        boolean status = customerService.delete(id);

        if(status){
            entity = new ResponseEntity(HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return entity;
    }

}
