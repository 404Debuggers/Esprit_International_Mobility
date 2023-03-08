package com.Debuggers.MobiliteInternational.Services.Impl;


import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.InterviewRepository;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSenderService {

    @Autowired
    CandidacyRepository candidacyRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    InterviewRepository interviewRepository;
    private final JavaMailSender mailSender;

    public EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    @Override
    public void sendEmail(Interview interview,String message,String subject) {
        /*String message="Bonjour" + interview.getCandidacy().getUser().getFirstName() +"," +
                 "Comme vous avez constater vous avez été accepter, felicitations. Veuillez consulter le calendrier donc notre site " +
                "pour consulter les horaires de l'entretien. En plus de cela, vous serez notifié un jour avant l'entretien";
         String subject="entretien mobilité";*/
        String to=interview.getCandidacy().getUser().getEmail();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("mobilityinternational29@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }


}
