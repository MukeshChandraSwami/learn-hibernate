package com.learn.service;

import com.learn.entity.Student;
import com.learn.entity.Teacher;
import com.learn.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TeacherService teacherService;

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    public Optional<Student> getStudent(long id) {
        return studentRepo.findById(id);
    }

    public boolean map(long teacherId, long studentId) {
        Optional<Teacher> teacherOp = teacherService.get(teacherId);
        Optional<Student> studentOp = studentRepo.findById(studentId);

        if (teacherOp.isPresent() && studentOp.isPresent()) {

            Student student = studentOp.get();
            Teacher teacher = teacherOp.get();

            student.setTeacher(teacher);
            return studentRepo.save(student) != null;
        }
        return false;
    }

    public boolean delete(long id) {
        studentRepo.deleteById(id);
        return true;
    }
}
