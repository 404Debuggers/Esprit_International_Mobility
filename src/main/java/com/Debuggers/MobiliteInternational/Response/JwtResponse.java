package com.Debuggers.MobiliteInternational.Response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";

    private String account;
    private List<String> roles;

    public JwtResponse(String accessToken, String account, List<String> roles) {
        this.token = accessToken;
        this.account = account;
        this.roles = roles;
    }
}
