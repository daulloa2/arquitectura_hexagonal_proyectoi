package com.hexagonal.crmarquitecturahexagonal.config;

import com.hexagonal.crmarquitecturahexagonal.domain.models.User;
import com.hexagonal.crmarquitecturahexagonal.ports.CarService;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientCarService;
import com.hexagonal.crmarquitecturahexagonal.ports.ClientService;
import com.hexagonal.crmarquitecturahexagonal.ports.RoleService;
import com.hexagonal.crmarquitecturahexagonal.ports.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component 
public class initTable implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    private ClientService clientService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private ClientCarService clientCarService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event){
        try {
            clientService.initTable();
        } catch (Exception e) {
            System.err.println("Error init data for clients: " + e);
        }

        try {
            roleService.initTable();
        } catch (Exception e) {
            System.err.println("Error init data for roles: " + e);
        }

        try {
            userService.initTable();
        } catch (Exception e) {

            System.err.println("Error init data for users: " + e);
        }

        try {
            userService.initTable();
        } catch (Exception e) {
            
            System.err.println("Error init data for users: " + e);
        }

        try {
            carService.initTable();
        } catch (Exception e) {
            
            System.err.println("Error init data for cars: " + e);
        }

        try {
            clientCarService.initTable();
        } catch (Exception e) {
            
            System.err.println("Error init data for client_car: " + e);
        }
    }
}
