package com.plunex.emilokan.modules.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service()
public class EmailSenderService implements IEmailSenderService {

    private final JavaMailSender javaMailSender;
    @Value("${email_sending.enabled}")
    private boolean emailSendingEnabled;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        if (emailSendingEnabled) {
            javaMailSender.send(email);
        }
    }
}
