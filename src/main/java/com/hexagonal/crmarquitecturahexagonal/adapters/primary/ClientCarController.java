package com.hexagonal.crmarquitecturahexagonal.adapters.primary;

import java.util.List;
import java.util.Locale;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/client-car")
public class ClientCarController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "")
    public  ResponseEntity<?> retrieveClientsWithCars(Locale locale){

        List<Client> clients = null;

        try {
            clients = clientService.findAll();
        } catch (Exception e) {
            System.err.println("Error retrieving clients with cars: "+ e.getMessage());
        }

        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }
    
}
