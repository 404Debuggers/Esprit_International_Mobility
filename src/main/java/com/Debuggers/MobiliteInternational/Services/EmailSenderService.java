package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Interview;
import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {


    void sendEmail(Interview interview,String message,String subject);

}
