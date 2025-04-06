// EmailService.java
package tn.esprit.microservice.kassil.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendNotification(String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("assilbelhaj12@gmail.com"); // Change to your own test email if needed
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
