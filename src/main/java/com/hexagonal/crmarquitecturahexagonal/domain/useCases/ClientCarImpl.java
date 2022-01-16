package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.Date;
import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.ClientCarRepository;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.domain.models.ClientCar;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Vehicle;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientCarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientCarImpl implements ClientCarService {

    @Autowired
    private ClientCarRepository clientCarRepository;

    @Override
    @Transactional
    public void initTable() {
        List<ClientCar> data = clientCarRepository.findAll();
        if (data.size() <= 0){
            clientCarRepository.initTable();
        }
        
    }

    @Override
    public void registerCarsToClient(Client client, Vehicle car) {
        ClientCar clientCar = new ClientCar();
        clientCar.setCar(car);
        clientCar.setClient(client);
        clientCar.setRegistrationDate(new Date());
        clientCarRepository.save(clientCar);
    

        
    }
    
}
