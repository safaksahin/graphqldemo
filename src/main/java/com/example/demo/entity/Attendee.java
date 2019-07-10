package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@GraphQLFederationKey(field = "id")
public class Attendee implements Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GraphQLFederationExternal
    private Long id;

    private String name;
    
    @OneToOne
    Talk talk;
}