package com.learn.service;

import com.learn.entity.Customer;
import com.learn.entity.Passport;
import com.learn.repo.PassportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassportService {

    @Autowired
    private PassportRepo passportRepo;

    public Optional<Passport> get(long id) {
        return passportRepo.findById(id);
    }

    public Passport save(Passport passport) {
        return passportRepo.save(passport);
    }

    public boolean delete(long id) {
        passportRepo.deleteById(id);
        return !get(id).isPresent();
    }
}
