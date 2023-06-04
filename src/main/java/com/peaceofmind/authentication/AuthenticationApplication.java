package com.peaceofmind.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peaceofmind.authentication.controller.utility_classes.RolesEnum;
import com.peaceofmind.authentication.model.Role;
import com.peaceofmind.authentication.repository.IRoleRepo;

@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	private final IRoleRepo roleRepo;

    @Autowired
    public AuthenticationApplication(IRoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

	@Override
	public void run(String... args) throws Exception {

		// Create three initial roles and save them to the database
        Role adminRole = new Role();
        adminRole.setRole(RolesEnum.ADMIN);
        roleRepo.save(adminRole);

        Role patientRole = new Role();
        patientRole.setRole(RolesEnum.PATIENT);
        roleRepo.save(patientRole);

        Role counselorRole = new Role();
        counselorRole.setRole(RolesEnum.COUNSELOR);
        roleRepo.save(counselorRole);
	}

}
