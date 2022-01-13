package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.ClientRepository;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void initTable() {
        List<Client> data = clientRepository.findAll();
        if (data.size() <= 0){
            clientRepository.initTable();
        }
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAllClients();
    }
    
}
