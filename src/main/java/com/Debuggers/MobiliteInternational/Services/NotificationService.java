package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.ReponseReport;

import javax.mail.MessagingException;

public interface NotificationService {
    void sendNotification(ReponseReport reponseReport) throws MessagingException;
}
