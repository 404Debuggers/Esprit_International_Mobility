package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.ReponseReport;
import com.Debuggers.MobiliteInternational.Repository.ReponseReportRepository;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import com.Debuggers.MobiliteInternational.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class NotificationServiceImpl implements NotificationService {
    @Autowired
    ReponseReportRepository r;
    @Autowired
    ReportRepository reportR;
    private JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }


    public void sendNotification(ReponseReport reponseReport)  {
        String message="Nous sommes heureux de vous informer que votre réclamation a été prise en compte et traitée conformément à notre politique de traitement des réclamations. Nous avons identifié les problèmes que vous avez soulevés et nous avons pris des mesures pour y remédier." +
                "";
        String subject="Réponse à votre réclamation";
        String to=reponseReport.getReport().getUser().getEmail();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("intmob94@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);

    }
}

