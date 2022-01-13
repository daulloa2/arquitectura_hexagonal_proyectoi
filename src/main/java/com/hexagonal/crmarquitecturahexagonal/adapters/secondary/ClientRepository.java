package com.hexagonal.crmarquitecturahexagonal.adapters.secondary;

import com.hexagonal.crmarquitecturahexagonal.config.InitData;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    @Modifying
    @Query(nativeQuery = true, value = " " + InitData.clientTable + " ")
    public void initTable();
}
