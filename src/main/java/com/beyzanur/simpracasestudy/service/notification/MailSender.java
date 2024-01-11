package com.beyzanur.simpracasestudy.service.notification;

import com.beyzanur.simpracasestudy.model.NotificationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSender implements NotificationSender {

    //@Value("${spring.mail.host}")
    //private String mailFrom;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendNotification(NotificationModel message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("");
        mailMessage.setTo(message.email());
        mailMessage.setSubject("Reservation Confirmation:" + message.confirmationNumber());
        mailMessage.setText(message.message());
        // please allow port 25 by configuring your firewall settings for sending email
        //javaMailSender.send(mailMessage);
    }
}
