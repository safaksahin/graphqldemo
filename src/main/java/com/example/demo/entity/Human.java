package com.example.demo.entity;

import javax.persistence.Version;

import io.leangen.graphql.annotations.types.GraphQLInterface;
import lombok.Data;

@Data
@GraphQLInterface(implementationAutoDiscovery = true,name = "human")
public class Human {

	@Version
    String version;

}
