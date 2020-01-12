package com.learn.service;

import com.learn.entity.Customer;
import com.learn.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

   //@Autowired
   EntityManagerFactory factory;

    /*@PersistenceContext
    private EntityManager entityManager;*/

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

    public Customer saveJpa(Customer customer) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist(customer);
        txn.commit();
        return customer;
    }
}
