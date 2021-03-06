package com.learn.service;

import com.learn.entity.Passport;
import com.learn.repo.PassportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassportService {

    @Value("${com.learn.data.source}")
    private String dataSource;

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
