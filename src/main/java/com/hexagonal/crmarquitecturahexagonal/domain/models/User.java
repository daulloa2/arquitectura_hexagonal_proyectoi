package com.hexagonal.crmarquitecturahexagonal.domain.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public User(){
        
    }
    public User(String uuid, String firstName, String lastName, String email, String phoneNumber, String address, Role role){

        this.uuid=uuid;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.role=role;
    }
}
