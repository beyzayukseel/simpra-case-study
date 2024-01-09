package com.beyzanur.simpracasestudy.service.notification;

import com.beyzanur.simpracasestudy.model.NotificationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSender implements NotificationSender {

    @Value("${confirmation.mail.address}")
    private String mailFrom;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendNotification(NotificationModel message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(message.email());
        mailMessage.setSubject("Reservation Confirmation:" + message.confirmationNumber());
        mailMessage.setText(message.message());
        javaMailSender.send(mailMessage);
    }
}
