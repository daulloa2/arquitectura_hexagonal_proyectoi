package com.hexagonal.crmarquitecturahexagonal.ports;
import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;

public interface ClientService {
    
    public void initTable();

    public List<Client> findAllClients();
}
