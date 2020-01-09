package com.learn.controller;

import com.learn.entity.Student;
import com.learn.service.StudentService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(path = "/{id}/get")
    public ResponseEntity getStudent(@PathVariable(required = true,name = "id") long id ){

        Optional<Student> savedStudent = studentService.getStudent(id);
        ResponseEntity entity = null;
        if(savedStudent.isPresent()){
            entity = new ResponseEntity(savedStudent, HttpStatus.OK);
        } else{
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return entity;
    }

    @PostMapping(path = "/save")
    public ResponseEntity save(@RequestBody Student student) {

        ResponseEntity entity = null;
        Student savedStudent = studentService.save(student);
        if (savedStudent != null)
            entity = new ResponseEntity("id : " + savedStudent.getId(), HttpStatus.OK);
        else
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);

        return entity;
    }

    @PutMapping(path = "/{teacherId}/with/{studentId}/map")
    public ResponseEntity mapWithTeacher(@PathVariable (required = true, name = "teacherId") long teacherId,
                                         @PathVariable (required = true, name = "studentId") long studentId){

        ResponseEntity entity = null;
        boolean status = studentService.map(teacherId, studentId);

        if(status) {
            entity = new ResponseEntity(HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

        return entity;
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable (required = true, name = "id") long id) {

        ResponseEntity entity = null;
        boolean status = studentService.delete(id);
        if(status) {
            entity = new ResponseEntity(HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
        return entity;
    }
}
