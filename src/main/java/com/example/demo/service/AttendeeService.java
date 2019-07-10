package com.example.demo.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Attendee;
import com.example.demo.entity.Talk;
import com.example.demo.repository.AttendeeRepository;

import graphql.introspection.Introspection;
import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLDirective;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;
    
    @GraphQLQuery
    public List<Attendee> findAllAttendee() {
        return attendeeRepository.findAll();
    }
    
    @GraphQLQuery
    public List<Attendee> findAllAttendiesForTalk(Talk talk) {
        List<Attendee> at = attendeeRepository.findAllByTalkId(talk.getId()).stream().collect(Collectors.toList());

        return at.stream()
                .map(e -> attendeeRepository.findById(e.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    
    @GraphQLMutation
    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }
    
    @GraphQLQuery
    public Input ex(Input input) {
    	return new Input();
    }
    
    @InputObjectType(@Wrapper(name = "input", value = "test"))
    private static class Input {
        @InputFieldDef(@Wrapper(name = "inputField", value = "test"))
        public String value;
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface Scalar {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface ObjectType {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    public @interface FieldDef {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER})
    public @interface ArgDef {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface InterfaceType {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface UnionType {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface EnumType {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface EnumValue {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface InputObjectType {
        @Meta("meta") Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    public @interface InputFieldDef {
        Wrapper value();
    }

    @GraphQLDirective
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface Meta {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.ANNOTATION_TYPE})
    public @interface Wrapper {
        String name();
        String value();
    }

    @GraphQLDirective(name = "timeout")
    public static class Interrupt {
        @GraphQLInputField(name = "afterMillis")
        public int after;
    }

    @GraphQLDirective(locations = Introspection.DirectiveLocation.FIELD)
    public static class Field {
        public boolean enabled;
    }

    @GraphQLDirective(locations = {Introspection.DirectiveLocation.FRAGMENT_DEFINITION, Introspection.DirectiveLocation.FRAGMENT_SPREAD, Introspection.DirectiveLocation.INLINE_FRAGMENT})
    public static class Frags {
        public boolean enabled;
    }

    @GraphQLDirective(locations = {Introspection.DirectiveLocation.QUERY, Introspection.DirectiveLocation.MUTATION})
    public static class Operation {
        public boolean enabled;
    }
}
