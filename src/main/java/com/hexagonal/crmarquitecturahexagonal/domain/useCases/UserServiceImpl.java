package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.UserRepository;
import com.hexagonal.crmarquitecturahexagonal.domain.models.User;
import com.hexagonal.crmarquitecturahexagonal.ports.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void initTable() {
        List<User> data = userRepository.findAll();
        if (data.size() <= 0){
            userRepository.initTable();
        }
        
    }
    
}
