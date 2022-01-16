package com.hexagonal.crmarquitecturahexagonal.ports;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Vehicle;

public interface ClientCarService {
    public void initTable();

    public void registerCarsToClient(Client client, Vehicle car);
}
