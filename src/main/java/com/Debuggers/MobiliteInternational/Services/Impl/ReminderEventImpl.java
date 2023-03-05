package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Event;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Services.EmailSenderService;
import com.Debuggers.MobiliteInternational.Services.ReminderEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ReminderEventImpl implements Runnable {

    @Autowired
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
        System.out.println("Reminder: " + event.getTitle() + " is in 24 hours.");


       // emailSenderService.sendEmail(interview);
    }

}
