package com.hexagonal.crmarquitecturahexagonal.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientDto {
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    private String uuid;

    @NotBlank
    private String uuidManager;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;
}
