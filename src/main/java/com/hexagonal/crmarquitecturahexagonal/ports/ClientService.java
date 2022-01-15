package com.hexagonal.crmarquitecturahexagonal.ports;
import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.dtos.CreateClientDto;
import com.hexagonal.crmarquitecturahexagonal.dtos.RetrieveClientDto;

public interface ClientService {
    
    public void initTable();

    public List<Client> findAllClients();
    
    public Client findByEmail(String email,String phoneNumber); 

    public void createClient(CreateClientDto client);

    public RetrieveClientDto findbyUuid (String uuid);

    public List<Client> findAll();
}
