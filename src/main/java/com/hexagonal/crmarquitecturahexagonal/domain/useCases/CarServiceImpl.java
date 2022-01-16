package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.CarRepository;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Vehicle;
import com.hexagonal.crmarquitecturahexagonal.ports.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional
    public void initTable() {
        List<Vehicle> data = carRepository.findAll();
        if (data.size() <= 0){
            carRepository.initTable();
        }        
    }

    @Override
    public Vehicle findById(Long idCar) {
        
        return carRepository.findByIdCar(idCar);
    }
    
}
