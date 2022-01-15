package com.hexagonal.crmarquitecturahexagonal.ports;

import com.hexagonal.crmarquitecturahexagonal.domain.models.Role;

public interface RoleService {
    public void initTable();

    public Role findByUuid(String uuid);
}
