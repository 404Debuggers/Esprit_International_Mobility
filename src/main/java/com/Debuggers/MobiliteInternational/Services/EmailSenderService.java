package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Interview;

public interface EmailSenderService {


    void sendEmail(Interview interview,String message,String subject);

}
