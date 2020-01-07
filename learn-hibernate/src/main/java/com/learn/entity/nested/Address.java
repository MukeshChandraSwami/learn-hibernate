package com.learn.entity.nested;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

    @Column(name = "student_address_street")
    private String street;
    @Column(name = "student_address_city")
    private String city;
    @Column(name = "student_address_state")
    private String state;
}