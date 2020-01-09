package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learn.entity.nested.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "student_details")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "student_id")
    private Long id;

    @NotNull
    @Column(name = "student_name")
    private String name;

    @NotNull
    @Column(name="student_class")
    private String cl;

    @Column(name = "student_class_section",nullable = false)
    private String section;

    @Embedded
    private Address address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id")
    @JsonBackReference      // This annotation at field level will remove this field from response.
    private Teacher teacher;
}
