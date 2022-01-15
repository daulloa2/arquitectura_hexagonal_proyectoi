package com.hexagonal.crmarquitecturahexagonal.adapters.secondary;

import com.hexagonal.crmarquitecturahexagonal.config.InitData;
import com.hexagonal.crmarquitecturahexagonal.domain.models.ClientCar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientCarRepository extends JpaRepository<ClientCar, Long>{
    
    @Modifying
    @Query(nativeQuery = true, value = " " + InitData.clientCarTable + " ")
    public void initTable();

    
}
