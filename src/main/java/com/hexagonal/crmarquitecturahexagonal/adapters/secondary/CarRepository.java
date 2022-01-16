package com.hexagonal.crmarquitecturahexagonal.adapters.secondary;

import com.hexagonal.crmarquitecturahexagonal.config.InitData;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Vehicle, Long> {
    
    @Modifying
    @Query(nativeQuery = true, value = " " + InitData.carTable + " ")
    public void initTable();

    @Query(nativeQuery = true, value = "SELECT * FROM cars WHERE cars.id_car = :idCar")
    public Vehicle findByIdCar(Long idCar);
}
