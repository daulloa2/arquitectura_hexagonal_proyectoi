package com.hexagonal.crmarquitecturahexagonal.adapters.secondary;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.config.InitData;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    @Modifying
    @Query(nativeQuery = true, value = " " + InitData.clientTable + " ")
    public void initTable();

    public Client findByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM client as c")
    public List<Client> findAllClients();

    @Query(nativeQuery = true, value = "SELECT * FROM client WHERE client.email= :email or client.phone_number= :phoneNumber")
    public Client findByEmailOrPhoneNumber(String email, String phoneNumber); 
}
