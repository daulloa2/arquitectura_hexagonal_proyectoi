package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.Date;
import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.UserRepository;
import com.hexagonal.crmarquitecturahexagonal.config.GenerateUUID;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Role;
import com.hexagonal.crmarquitecturahexagonal.domain.models.User;
import com.hexagonal.crmarquitecturahexagonal.dtos.CreateUserDto;
import com.hexagonal.crmarquitecturahexagonal.ports.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void initTable() {
        List<User> data = userRepository.findAll();
        if (data.size() <= 0){
            userRepository.initTable();
        }
        
    }

    @Override
    public List<User> findAllUsers() {
        
        return userRepository.findAll();
    }

    @Override
    public User findbyUuid(String uuidUser) {
        
        return userRepository.findByUuid(uuidUser);
    }

    @Override
    public User findByEmailOrPhoneNumber(String email, String phoneNumber) {
        
        return userRepository.findByEmailOrPhoneNumber(email, phoneNumber);
    }

    @Override
    public void createUser(CreateUserDto userDto, Role role) {
        
        userDto.setUuid(GenerateUUID.generateUUID(new Date().toString()+userDto.getEmail()+userDto.getPhoneNumber()));

        User user = new User(userDto.getUuid(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getUsername(), userDto.getPassword(), role);

        userRepository.save(user);
        
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
