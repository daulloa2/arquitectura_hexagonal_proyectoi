package com.hexagonal.crmarquitecturahexagonal.config;

import com.hexagonal.crmarquitecturahexagonal.puertos.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component 
public class initTable implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    private ClientService clientService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event){
        try {
            clientService.initTable();
        } catch (Exception e) {
            System.err.println("Error init data for clients" + e);
        }
    }
}
