package com.peaceofmind.authentication.service;

import java.util.Optional;
import java.util.Set;

import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.model.UserRoles;

public interface IUserService {

    //creating user
    User createUser(User user);
    Optional<User> getUser(Long id);
}
