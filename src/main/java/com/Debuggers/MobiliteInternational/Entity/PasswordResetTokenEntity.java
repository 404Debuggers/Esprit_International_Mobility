package com.Debuggers.MobiliteInternational.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PasswordResetTokenEntity  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private String code;

    @OneToOne
    @JoinColumn(name="users_idUser")
    private User userDetails;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public User getUserDetails() {
        return userDetails;
    }
    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
