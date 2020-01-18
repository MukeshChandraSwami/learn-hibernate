package com.learn.repo;

import com.learn.entity.Customer;
import com.learn.enums.CustomerNamedQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    default Customer getStudentByIdUsingQuery(EntityManager entityManager, Long id) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("select c from Customer as c where c.id = :id")
                    .setParameter("id",id);
        entityManager.flush();
        transaction.commit();
       return (Customer) query.getSingleResult();
    }

    default Customer getStudentByIdUsingNamedQuery(EntityManager entityManager, Long id) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNamedQuery(CustomerNamedQuery.GET_BY_ID)
                .setParameter("id",id);
        entityManager.flush();
        transaction.commit();
        return (Customer) query.getSingleResult();
    }

    default long countViaQuery(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT count(c) from Customer as c");

        long result = (long) query.getSingleResult();
        entityManager.flush();
        transaction.commit();
        return result;
    }

    default long countViaEM(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = criteriaBuilder.createQuery();
        query.select(criteriaBuilder.count(query.from(Customer.class)));
        long result = (long) entityManager.createQuery(query).getSingleResult();
        entityManager.flush();
        transaction.commit();
        return result;
    }

    default Customer getByNamedQuery(EntityManager entityManager, long id){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = null;
        try {
            Query query = entityManager.createNamedQuery(CustomerNamedQuery.GET_BY_ID,Customer.class)
                    .setParameter("id", id);
            customer = (Customer) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.flush();
            transaction.commit();
        }
        return customer;
    }
}
