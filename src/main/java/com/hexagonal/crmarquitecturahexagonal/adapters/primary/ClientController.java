package com.hexagonal.crmarquitecturahexagonal.adapters.primary;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllClients(){
        
        List<Client> clients = null;
        try {
            clients = clientService.findAllClients();
        } catch (Exception e) {
            System.err.println("Error for loading clients");
        }

        // for (Client client : clients) {
        //     Client c = new Client();
        //     c = client;
        //     System.out.println(c.getFirstName());
        // }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }


    
}
