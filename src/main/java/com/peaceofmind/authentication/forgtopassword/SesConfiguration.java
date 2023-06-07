package com.peaceofmind.authentication.forgtopassword;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import io.awspring.cloud.mail.simplemail.SimpleEmailServiceMailSender;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;


@Configuration
public class SesConfiguration {
    @Value("${aws.accessKey}")
    private String access_key;
    @Value("${aws.secretKey}")
    private String secret_kay;
    @Value("${aws.region}")
    private String region;


    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(access_key, secret_kay);
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }

    
}
