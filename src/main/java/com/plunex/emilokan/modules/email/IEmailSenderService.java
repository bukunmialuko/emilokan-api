package com.plunex.emilokan.modules.email;

import org.springframework.mail.SimpleMailMessage;

public interface IEmailSenderService {
    void sendEmail(SimpleMailMessage email);
//    void sendEmail(Mail email) throws IOException;
}
