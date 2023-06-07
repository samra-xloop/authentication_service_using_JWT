package com.peaceofmind.authentication.forgtopassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peaceofmind.authentication.model.User;

@Repository
public interface IforgotPasswordRepository extends JpaRepository<ForgotPasswordRequest, Long>{

    ForgotPasswordRequest save(ForgotPasswordRequest request);
    // ForgotPasswordRequest findByEmail(String email);
    

}
