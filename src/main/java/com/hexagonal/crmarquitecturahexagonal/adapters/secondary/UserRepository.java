package com.hexagonal.crmarquitecturahexagonal.adapters.secondary;

import com.hexagonal.crmarquitecturahexagonal.config.InitData;
import com.hexagonal.crmarquitecturahexagonal.domain.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Modifying
    @Query(nativeQuery = true, value = " " + InitData.userTable + " ")
    public void initTable();

    public User findByUuid(String uuid);

    public User findByEmailOrPhoneNumber(String email, String phoneNumber);

    public User findByUsername(String username);
}
