package com.hexagonal.crmarquitecturahexagonal.adapters.primary;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.BadRequestException;
import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.NotFoundException;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Client;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Vehicle;
import com.hexagonal.crmarquitecturahexagonal.dtos.AssignClientToCarsDto;
import com.hexagonal.crmarquitecturahexagonal.ports.CarService;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientCarService;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/client-car")
public class ClientCarController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientCarService clientCarService;

    @Autowired
    private CarService carService;

    @Autowired
    MessageSource messages;

    @GetMapping(value = "")
    public ResponseEntity<?> retrieveClientsWithCars(Locale locale) {

        List<Client> clients = null;

        try {
            clients = clientService.findAll();
        } catch (Exception e) {
            System.err.println("Error retrieving clients with cars: " + e.getMessage());
        }

        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> assignClientoToCars(@RequestBody AssignClientToCarsDto assignClientToCarsDto,
            BindingResult result, Locale locale) {

        if (result.hasErrors()) {
            String message = result.getFieldErrors().stream().map(err -> err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.joining(" \n"));
            BadRequestException badRequest = new BadRequestException(message);
            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // Verificar si el cliente existe y pertenece al usuario que se desea asignar

        Client client = null;
        try {
            client = clientService.findByUuidClient(assignClientToCarsDto.getUuidClient());
        } catch (Exception e) {
            System.err.println("Error retrieving client by uuid: " + e.getMessage());
        }

        if (client == null) {
            NotFoundException notFoundException = new NotFoundException(
                    messages.getMessage("error.NotFoundClient", null, locale));
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            if (!client.getUuidUserManager().equals(assignClientToCarsDto.getUuidUserManager())) {
                BadRequestException badRequest = new BadRequestException(
                        messages.getMessage("error.ClientNotUserManager", null, locale));
                return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        // Verificar que todos los carros existan

        for (Long car : assignClientToCarsDto.getIdsCars()) {

            // Verificamos
            Vehicle carToDB = null;
            try {
                carToDB = carService.findById(car);
            } catch (Exception e) {
                System.err.println("Error retrieving cars: " + e.getMessage());
            }

            if (carToDB != null) {
                // Send cars to client
                try {
                    clientCarService.registerCarsToClient(client, carToDB);
                } catch (Exception e) {
                    System.err.println("Error saving cars for client: " + e.getMessage());
                }
            }

        }

        return new ResponseEntity<>(messages.getMessage("message.SuccessfullyRegisterded", null, locale),
                HttpStatus.CREATED);
    }

}
