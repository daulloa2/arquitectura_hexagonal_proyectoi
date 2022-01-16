package com.hexagonal.crmarquitecturahexagonal.adapters.primary;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.BadRequestException;
import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.InternalServerException;
import com.hexagonal.crmarquitecturahexagonal.domain.exceptions.NotFoundException;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Role;
import com.hexagonal.crmarquitecturahexagonal.domain.models.User;
import com.hexagonal.crmarquitecturahexagonal.dtos.CreateUserDto;
import com.hexagonal.crmarquitecturahexagonal.ports.RoleService;
import com.hexagonal.crmarquitecturahexagonal.ports.UserService;

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
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllClients(Locale locale){
        
        List<User> users = null;
        try {
            users = userService.findAllUsers();
        } catch (Exception e) {
            System.err.println("Error for loading clients: "+ e.getMessage());
        }

        if(users == null) {
            NotFoundException notFoundException = new NotFoundException( messages.getMessage("error.NotFoundUsers", null, locale));
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    } 


    @GetMapping(value = "/{uuidUser}")
    public ResponseEntity<?> getClient(@PathVariable String uuidUser, Locale locale){
        
        User user = null;
        try {
            user = userService.findbyUuid(uuidUser);
        } catch (Exception e) {
            System.err.println("Error retrieving client: "+ e.getMessage());
        }

        if(user == null) {
            NotFoundException notFoundException = new NotFoundException( messages.getMessage("error.NotFoundUser", null, locale));
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<?>createUser(@Valid @RequestBody CreateUserDto createUser, BindingResult result, Locale locale){

        // Capturar errores del usuario para campos obligatorios
        if(result.hasErrors()){
            String message = result.getFieldErrors().stream().map(err -> err.getField() + " " + err.getDefaultMessage()).collect(Collectors.joining(" \n"));
            BadRequestException badRequest = new BadRequestException(message);
            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        }

        // Verificar username
        User verifiedUsername = null;

        try {
            verifiedUsername = userService.findByUsername(createUser.getUsername());
        } catch (Exception e) {
            System.err.println("Error retrieve user by username : "+ e.getMessage());
            throw new InternalServerException("Internal Server Error: "+ e.getMessage());
        }

        if (verifiedUsername != null) {
            BadRequestException badRequest = new BadRequestException( messages.getMessage("error.usernameAlreadyExists", null, locale));

            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        }

        // Verificar rol
        Role role= null;
        try {
            role = roleService.findByUuid(createUser.getUuidRole());
        } catch (Exception e) {
            System.err.println("Error retrieving role: "+ e.getMessage());
            throw new InternalServerException("Internal Server Error: "+ e.getMessage());
        }

        if (role == null){
            BadRequestException badRequest = new BadRequestException( messages.getMessage("error.NotFoundRole", null, locale));

            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        }

        User user = null;

        // Verificar correo y telefóno
        try {
            user = userService.findByEmailOrPhoneNumber(createUser.getEmail(), createUser.getPhoneNumber());
        } catch (Exception e) {
            System.err.println("Error retrieving user: "+ e.getMessage());
            throw new InternalServerException("Internal Server Error: "+ e.getMessage());
        }

        if (user != null){
            BadRequestException badRequest = new BadRequestException( messages.getMessage("error.EmailOrNumberPhoneAlreadyExists", null, locale));

            return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        } 

        // Crear cliente
        try {
           userService.createUser(createUser, role);
        } catch (Exception e) {
            System.err.println("Error saving user: "+ e.getMessage());
            throw new InternalServerException("Internal Server Error: "+ e.getMessage());
        }

        return new ResponseEntity<>(messages.getMessage("message.createUser", null, locale), HttpStatus.CREATED);
    }


    
}
