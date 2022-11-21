package br.com.quarkus.rest.resource.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
}