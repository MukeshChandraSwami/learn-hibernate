package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learn.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer_details")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "customer_name",nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    //@Type(type = "com.learn.enums.CustomerType")
    private CustomerType customerType;

    @ElementCollection
    @CollectionTable(name = "customer_hobbies_details"/*,joinColumns = @JoinColumn(name = "customer_id")*/)
    @JoinColumn(name = "customer_id")
    @Column(name = "customer_hobie")
    private List<String> hobbies;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id",unique = true)
    private Passport passport;

}
