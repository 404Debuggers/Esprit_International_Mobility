package com.Debuggers.MobiliteInternational.Entity;


import java.util.Date;


// Serialization du post de interview depuis le json du postman ,comme un dto
// lazem il body tall il post ili jey mil front kon nafs il forme taa l classe hathiya si nn les attributs bch reyeaadew null
public class Interview_Event extends Interview {

    private String link;
    private Boolean archive;
    private Date eventDate;


    public Date getEventDate() {
        return eventDate;
    }


}
