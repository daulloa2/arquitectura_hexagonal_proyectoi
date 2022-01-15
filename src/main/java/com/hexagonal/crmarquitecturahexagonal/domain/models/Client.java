package com.hexagonal.crmarquitecturahexagonal.domain.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client implements Serializable {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    
    @NotBlank
    @Column(unique = true, updatable = false)
    private String uuid; 

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String uuidUserManager;

    @JsonManagedReference
    @OneToMany(mappedBy =  "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientCar> clientCar;

    public Client(){
        
    }
    public Client(String uuid, String firstName, String lastName, String email, String phoneNumber, String address, String uuidUserManager){

        this.uuid=uuid;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.uuidUserManager=uuidUserManager;
    }

}
