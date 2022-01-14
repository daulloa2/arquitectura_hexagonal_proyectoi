package com.hexagonal.crmarquitecturahexagonal.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_car;

    @NotBlank
    private String model;

    @NotBlank
    private String brand;

    @NotBlank
    private String year;

    @NotBlank
    private String color;

    @NotBlank
    private String transmission;

    @NotNull
    private Long stock;

    @NotNull
    private BigDecimal price;

    @JsonBackReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientCar> clientCar;

}
