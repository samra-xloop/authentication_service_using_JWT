package com.peaceofmind.authentication.service;

import java.util.Optional;
import java.util.Set;

import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.model.UserRoles;

public interface IUserService {

    //creating user
    User creatUser(User user, Set<UserRoles> userRoles);
    Optional<User> getUser(Long id);
}
