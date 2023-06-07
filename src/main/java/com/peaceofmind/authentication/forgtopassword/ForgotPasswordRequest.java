package com.peaceofmind.authentication.forgtopassword;
import java.time.LocalDateTime;

import com.peaceofmind.authentication.model.User;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "forgot_password_requests")
public class ForgotPasswordRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;
    
    @Column(nullable = false)
    private String otp;
    
    @Column(nullable = false)
    private LocalDateTime expirationDate;
    
    @Column(nullable = false)
    private boolean isUsed=false;
}