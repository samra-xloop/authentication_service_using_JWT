package com.peaceofmind.authentication.service.imp;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peaceofmind.authentication.controller.UserController;
import com.peaceofmind.authentication.model.Role;
import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.model.UserRoles;
import com.peaceofmind.authentication.repository.IRoleRepo;
import com.peaceofmind.authentication.repository.IUserRepo;
import com.peaceofmind.authentication.service.IUserService;
import com.peaceofmind.authentication.utility_classes.RolesEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final IUserRepo userRepository;
    private final IRoleRepo roleRepo;

    
    @Override
    public User createUser(User user) {
        Set<UserRoles> userRoles = new HashSet<>();
        // Create a new role based on the user's role field
        for (RolesEnum role : user.getRoles()) {
            Role existingRole = roleRepo.findByRole(role);
            UserRoles userRole = new UserRoles();
            userRole.setRole(existingRole);
            userRole.setUser(user);
            userRoles.add(userRole);
        }

        user.setUserRoles(userRoles);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    

}
