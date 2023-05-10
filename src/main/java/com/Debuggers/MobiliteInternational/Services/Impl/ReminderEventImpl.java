package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Event;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Services.EmailSenderService;
import com.Debuggers.MobiliteInternational.Services.ReminderEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ReminderEventImpl implements Runnable {


    private  EmailSenderService emailSenderService;
    private Event event;
    private Interview interview;

    public ReminderEventImpl(Event event, Interview interview) {
        this.event = event;
        this.interview = interview;

    }



    public void run() {
        // Send the reminder notification using an email service, a push notification service, etc.
        // For example:

        System.out.println(interview.getInterviewId());
        System.out.println("Mr/Mme " + event.getTitle() + " je vous rappele que votre entretien ce passe demain a cette heure");

        /*String message="Bonjour" + event.getInterview().getCandidacy().getUser().getFirstName() +"," +
                "Comme vous avez constater vous avez été accepter, felicitations. Veuillez consulter le calendrier donc notre site " +
                "pour consulter les horaires de l'entretien. En plus de cela, vous serez notifié un jour avant l'entretien";
        String subject="entretien mobilité";
       emailSenderService.sendEmail(event.getInterview(),message,subject);*/
    }

}
