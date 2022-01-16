package com.hexagonal.crmarquitecturahexagonal.ports;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Vehicle;

public interface CarService {
    public void initTable();

    public Vehicle findById(Long idCar);
}
