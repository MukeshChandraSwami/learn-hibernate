package com.learn.service;

import com.learn.entity.Teacher;
import com.learn.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    StudentService studentService;

    public Optional<Teacher> get(long id) {
        return teacherRepo.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public boolean delete(long id) {
        teacherRepo.deleteById(id);
        return get(id).isPresent();
    }
}
