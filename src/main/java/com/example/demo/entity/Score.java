package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@GraphQLType(name = "score")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String title;
    private Integer score;
}
