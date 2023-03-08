package com.Debuggers.MobiliteInternational.Security.Services;

import org.springframework.stereotype.Component;


public interface IMailService {

    void sendEmail(String to , String objet , String message);


}
