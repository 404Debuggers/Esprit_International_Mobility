package com.Debuggers.MobiliteInternational.DTO;

public class PasswordResetRequestModel {

    private String email;
    private Long phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
