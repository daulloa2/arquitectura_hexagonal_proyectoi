package com.hexagonal.crmarquitecturahexagonal.adapters.secondary;

import com.hexagonal.crmarquitecturahexagonal.config.InitData;
import com.hexagonal.crmarquitecturahexagonal.domain.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Modifying
    @Query(nativeQuery = true, value = " " + InitData.roleTable + " ")
    public void initTable();

    public Role findByUuid(String uuid);
}
