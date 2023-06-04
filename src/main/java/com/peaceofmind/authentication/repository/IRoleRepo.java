package com.peaceofmind.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peaceofmind.authentication.controller.utility_classes.RolesEnum;
import com.peaceofmind.authentication.model.Role;

public interface IRoleRepo extends JpaRepository<Role, Long>{
    

    Role findByRole(RolesEnum role);
}
