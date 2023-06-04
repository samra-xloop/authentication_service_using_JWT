package com.peaceofmind.authentication.service.imp;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.model.UserRoles;
import com.peaceofmind.authentication.repository.IUserRepo;
import com.peaceofmind.authentication.service.IUserService;

@Service
public class UserServiceImp implements IUserService {

    private final IUserRepo userRepository;

    @Autowired
    public UserServiceImp(IUserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User creatUser(User user, Set<UserRoles> userRoles) {
        user.setUserRoles(userRoles);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}


