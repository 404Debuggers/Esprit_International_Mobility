package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Enum.ERole;
import com.Debuggers.MobiliteInternational.Entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User save(User utilisateur,Long idRole);

    public User update(User utilisateur);
    public void delete(Long code);

    public User getUserById(Long id );

    public User findOne(String email);



    public void assignRoleToUser(Long idUser , Long roleId );


    User loadUserByEmail(String email);


    boolean requestPasswordReset(String email) throws Exception;

    boolean resetPasswordMail(String email, String token) throws UnsupportedEncodingException, MessagingException;
}
