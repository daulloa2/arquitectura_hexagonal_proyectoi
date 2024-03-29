package com.hexagonal.crmarquitecturahexagonal.adapters.primary;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.BadRequestException;
import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.InternalServerException;
import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.NotFoundException;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.dtos.CreateClientDto;
import com.hexagonal.crmarquitecturahexagonal.dtos.RetrieveClientDto;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    MessageSource messages;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllClients(Locale locale){
        
        List<Client> clients = null;
        try {
            clients = clientService.findAllClients();
        } catch (Exception e) {
            System.err.println("Error for loading clients: "+ e.getMessage());
        }

        if(clients == null) {
            NotFoundException notFoundException = new NotFoundException( messages.getMessage("error.NotFoundClients", null, locale));
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuidClient}")
    public ResponseEntity<?> getClient(@PathVariable String uuidClient, Locale locale){
        
        RetrieveClientDto client = null;
        try {
            client = clientService.findbyUuid(uuidClient);
        } catch (Exception e) {
            System.err.println("Error retrieving client: "+ e.getMessage());
        }

        if(client == null) {
            NotFoundException notFoundException = new NotFoundException( messages.getMessage("error.NotFoundClient", null, locale));
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?>createClient(@Valid @RequestBody CreateClientDto createClient, BindingResult result, Locale locale){

        // Capturar errores del client para campos obligatorios
        if(result.hasErrors()){
            String message = result.getFieldErrors().stream().map(err -> err.getField() + " " + err.getDefaultMessage()).collect(Collectors.joining(" \n"));
            BadRequestException badRequest = new BadRequestException(message);
            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        }

        Client client;

        // Verificar correo y telefóno
        try {
            client = clientService.findByEmail(createClient.getEmail(), createClient.getPhoneNumber());
        } catch (Exception e) {
            System.err.println("Error retrieving client: "+ e.getMessage());
            throw new InternalServerException("Internal Server Error: "+ e.getMessage());
        }

        if (client != null){
            BadRequestException badRequest = new BadRequestException( messages.getMessage("error.EmailOrNumberPhoneAlreadyExists", null, locale));

            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        } 

        // Crear cliente
        try {
           clientService.createClient(createClient);
        } catch (Exception e) {
            System.err.println("Error saving client: "+ e.getMessage());
            throw new InternalServerException("Internal Server Error: "+ e.getMessage());
        }

        return new ResponseEntity<>(messages.getMessage("message.createUser", null, locale), HttpStatus.CREATED);
    }


    
}
