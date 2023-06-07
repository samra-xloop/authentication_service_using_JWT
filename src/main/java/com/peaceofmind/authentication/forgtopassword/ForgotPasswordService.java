package com.peaceofmind.authentication.forgtopassword;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.repository.IUserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


@Service
@RequiredArgsConstructor
public class ForgotPasswordService {

    private final IUserRepo userRepo;
    private final IforgotPasswordRepository FP_Repo;

    private final MailSender mailSender;

    @Value("${otp.expiration.minutes}")
    private int OTP_EXPIRATION_MINUTES;

    @Value("${simple.mail.message.subject}")
    private String SIMPLE_MAIL_MESSAGE_SUBJECT;

    @Value("${simple.mail.message.message}")
    private String SIMPLE_MAIL_MESSAGE_MESSAGE;

    private String otp;


    public void sendResetPasswordEmail(String email) {
        saveRequest(email);
        sendEmail(email);
    }

    public void saveRequest(String email) {
        User user = userRepo.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));

        otp = generateOTP();

        ForgotPasswordRequest request = ForgotPasswordRequest.builder()
                .otp(otp)
                .user(user)
                .expirationDate(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES))
                .isUsed(false)
                .build();

        FP_Repo.save(request);
    }

    public String sendEmail(String email) {
        String senderEmail = "your-sender-email@example.com"; // Replace with your sender email address

        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setFrom(senderEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(SIMPLE_MAIL_MESSAGE_SUBJECT);
        simpleMailMessage.setText(SIMPLE_MAIL_MESSAGE_MESSAGE + otp );

        

        this.mailSender.send(simpleMailMessage);

        return "Email Send successfully";

        
        
    }

    private String generateOTP() {
        int otpLength = 5; // Length of the OTP
        String allowedChars = "0123456789"; // Characters allowed in the OTP

        return new Random().ints(otpLength, 0, allowedChars.length())
                .mapToObj(allowedChars::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

}
