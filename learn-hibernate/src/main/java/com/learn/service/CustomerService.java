package com.learn.service;

import com.learn.entity.Customer;
import com.learn.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    @Qualifier("entityManager")
    private EntityManager entityManager;

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

    public Customer saveByEm(Customer customer) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist(customer);
        entityManager.flush();
        txn.commit();
        return customer;
    }

    public Optional<Customer> getByEm(long id) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.flush();
        txn.commit();
        return Optional.of(customer);
    }

    public Optional<Customer> getByQuery(Long id){
        return Optional.of(customerRepo.getStudentByIdUsingQuery(entityManager, id));
    }

    public Optional<Customer> getByNamedQuery(long id){
        return Optional.of(customerRepo.getByNamedQuery(entityManager,id));
    }

    public long countViaEM() {
        return customerRepo.countViaEM(entityManager);
    }

    public long countViaQuery() {
        return customerRepo.countViaQuery(entityManager);
    }
}
