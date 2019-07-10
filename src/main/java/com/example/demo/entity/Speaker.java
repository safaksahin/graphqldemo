package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@FieldDefaults(makeFinal = false, level = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Speaker extends Human implements Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String twitter;
    
    @OneToOne
    Talk talk;
}