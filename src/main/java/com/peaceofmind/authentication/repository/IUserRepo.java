package com.peaceofmind.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peaceofmind.authentication.model.User;

public interface IUserRepo extends JpaRepository<User, Long>{
    
}
