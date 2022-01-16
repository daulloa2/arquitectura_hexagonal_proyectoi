package com.hexagonal.crmarquitecturahexagonal.ports;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Role;
import com.hexagonal.crmarquitecturahexagonal.domain.models.User;
import com.hexagonal.crmarquitecturahexagonal.dtos.CreateUserDto;

public interface UserService {
    
    public void initTable();

    public List<User> findAllUsers();

    public User findbyUuid(String uuidUser);

    public User findByEmailOrPhoneNumber(String email, String phoneNumber);

    public void createUser(CreateUserDto userDto, Role role);

    public User findByUsername(String username);
}
