package com.learn.controller;

import com.learn.entity.Teacher;
import com.learn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/{id}/get")
    public ResponseEntity get(@PathVariable (required = true,name = "id") long id){

        Optional<Teacher> teacher = teacherService.get(id);
        ResponseEntity entity;
        if(teacher.isPresent()){
            entity = new ResponseEntity(teacher.get(), HttpStatus.OK);
        } else{
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return entity;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Teacher teacher){
        ResponseEntity entity;

        Teacher savedTeacher = teacherService.save(teacher);

        if(savedTeacher != null){
            entity = new ResponseEntity("Id :- " + savedTeacher.getId(),HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return entity;
    }
}
