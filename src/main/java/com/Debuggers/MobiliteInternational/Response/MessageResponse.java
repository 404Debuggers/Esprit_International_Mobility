package com.Debuggers.MobiliteInternational.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
