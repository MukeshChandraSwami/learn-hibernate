package com.learn.service;

import com.learn.entity.Customer;
import com.learn.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public Optional<Customer> getById(long id) {
        return customerRepo.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    public boolean delete(long id) {
        customerRepo.deleteById(id);
        return !getById(id).isPresent();
    }
}
