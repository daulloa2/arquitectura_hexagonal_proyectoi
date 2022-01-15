package com.hexagonal.crmarquitecturahexagonal.domain.useCases;

import java.util.List;

import com.hexagonal.crmarquitecturahexagonal.adapters.secondary.RoleRepository;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Role;
import com.hexagonal.crmarquitecturahexagonal.ports.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void initTable() {
        List<Role> data = roleRepository.findAll();
        if (data.size() <= 0){
            roleRepository.initTable();
        }
    }

    @Override
    public Role findByUuid(String uuid) {
        return roleRepository.findByUuid(uuid);
    }

    
    

}
