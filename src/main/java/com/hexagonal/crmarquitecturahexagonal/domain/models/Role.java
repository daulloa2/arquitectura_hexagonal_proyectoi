package com.hexagonal.crmarquitecturahexagonal.domain.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    
    @NotBlank
    @Column(unique = true, updatable = false)
    private String uuid;

    @NotBlank
    private String roleName;

    @JsonBackReference
    @OneToMany(mappedBy =  "role")
    private Set<User> role;
}
