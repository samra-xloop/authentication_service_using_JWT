package com.peaceofmind.authentication.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peaceofmind.authentication.controller.utility_classes.RolesEnum;
import com.peaceofmind.authentication.model.Role;
import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.model.UserRoles;
import com.peaceofmind.authentication.repository.IRoleRepo;
import com.peaceofmind.authentication.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final IRoleRepo roleRepo;

    @Autowired
    public UserController(IUserService userService, IRoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        Set<UserRoles> userRoles=new HashSet<>();
        // Create a new role based on the user's role field
        for ( RolesEnum role: user.getRoles()){
            Role existingrole = roleRepo.findByRole(role);
            UserRoles userRole= new UserRoles();
            userRole.setRole(existingrole);
            userRole.setUser(user);

            userRoles.add(userRole);

        }

        

        // Set the userRoles list in the user object
        user.setUserRoles(userRoles);

        User createdUser = userService.creatUser(user, userRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getAUser(@PathVariable Long id) {
        Optional<User> foundUser = userService.getUser(id);
        if (foundUser.isPresent()) {
            return new ResponseEntity<>(foundUser.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

}
