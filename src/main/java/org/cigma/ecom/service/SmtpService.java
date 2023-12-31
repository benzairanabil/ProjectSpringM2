package org.cigma.ecom.service;

import org.cigma.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpService {
    private final JavaMailSender javaMailSender;


    @Autowired
    public SmtpService (JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void SendMail(User compt) throws MailException {
        //send mail
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(compt.getEmail());
        mail.setFrom("cigma.d2j.2020@gmail.com");
        mail.setSubject("Spring mail notification test");
        mail.setText("TEST MESSAGE");

        javaMailSender.send(mail);
    }
}
