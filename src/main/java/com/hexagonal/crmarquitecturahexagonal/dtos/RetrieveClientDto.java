package com.hexagonal.crmarquitecturahexagonal.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveClientDto {
    
    private String uuid; 

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    public RetrieveClientDto(){

    }

    public RetrieveClientDto ( String uuid, String firstName, String lastName, String email, String phoneNumber, String address){
        this.uuid=uuid;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }
}
