package com.hexagonal.crmarquitecturahexagonal.domain.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client_car")
public class ClientCar implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClientCar;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_client")
    private Client client;

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_car")
    private Vehicle car;
}
