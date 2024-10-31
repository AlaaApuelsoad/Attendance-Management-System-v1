package com.demo.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {






    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("alaaapu135@gmail.com");
        message.setSubject("Test Mail Sending");
        message.setText("Verification code");
        mailSender.send(message);
        System.out.println("Email sent successfully.");
    }


}
