package br.com.quarkus.rest.resource.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {
    Long id;
    String name;
    String email;
}
