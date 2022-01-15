package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.Date;
import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.ClientRepository;
import com.hexagonal.crmarquitecturahexagonal.config.GenerateUUID;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.dtos.CreateClientDto;
import com.hexagonal.crmarquitecturahexagonal.dtos.RetrieveClientDto;
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

    @Override
    public Client findByEmail(String email, String phoneNumber) {
        return clientRepository.findByEmailOrPhoneNumber(email, phoneNumber);
    }

    @Override
    public void createClient(CreateClientDto clientDto) {
        // Generate uuid for client
        clientDto.setUuid(GenerateUUID.generateUUID(new Date().toString()+clientDto.getEmail()+clientDto.getPhoneNumber()));

        // Save client
        Client client = new Client(clientDto.getUuid(), clientDto.getFirstName(), clientDto.getLastName(), clientDto.getEmail(), clientDto.getPhoneNumber(), clientDto.getAddress(), clientDto.getUuidManager());
        
         clientRepository.save(client);
    }

    @Override
    public RetrieveClientDto findbyUuid(String uuid) {
        Client client = clientRepository.findByUuid(uuid);
        RetrieveClientDto clientDto = new RetrieveClientDto(client.getUuid(), client.getFirstName(), client.getLastName(), client.getEmail(), client.getPhoneNumber(), client.getAddress() );
        return clientDto;
    }

    @Override
    public List<Client> findAll() {
        
        return clientRepository.findAll();
    }
    
}
