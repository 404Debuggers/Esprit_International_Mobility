package com.Debuggers.MobiliteInternational.Entity;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



public class Interview_Event extends Interview {

    private String link;
    private Boolean archive;
    private Date eventDate;


    public LocalDateTime getEventDate() {
        LocalDateTime localDateTime = eventDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }


}
