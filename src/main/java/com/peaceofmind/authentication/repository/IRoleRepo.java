package com.peaceofmind.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peaceofmind.authentication.model.Role;
import com.peaceofmind.authentication.utility_classes.RolesEnum;

public interface IRoleRepo extends JpaRepository<Role, Long>{
    

    Role findByRole(RolesEnum role);
}
