package com.hexagonal.crmarquitecturahexagonal.dtos;

import javax.validation.constraints.NotBlank;

public class RetrieveAllClientsDto {
    
    @NotBlank
    private String uuid; 

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String uuidUser;
}
