package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@GraphQLFederationKey(field = "id")
@EqualsAndHashCode(callSuper = true)
public class Attendee extends Human implements Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GraphQLFederationExternal
    private Long id;

    private String name;
    
    @OneToOne
    Talk talk;
}