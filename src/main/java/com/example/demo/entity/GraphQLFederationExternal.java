package com.example.demo.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import graphql.introspection.Introspection.DirectiveLocation;
import io.leangen.graphql.annotations.types.GraphQLDirective;

@GraphQLDirective(name = "external", locations = DirectiveLocation.FIELD, description = "Can be used for GraphQL Federation")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface GraphQLFederationExternal {}
