package com.Debuggers.MobiliteInternational.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService{
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to , String objet , String message) {


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(objet);
        mail.setText(message);
        mail.setSubject(objet);
        javaMailSender.send(mail);
    }
}
