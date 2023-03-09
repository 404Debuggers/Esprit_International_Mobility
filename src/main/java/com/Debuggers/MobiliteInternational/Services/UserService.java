package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User save(User utilisateur, Long idRole);

    User update(User utilisateur);

    void delete(Long code);

    User getUserById(Long id);

    User findOne(String email);

    void assignRoleToUser(Long idUser, Long roleId);

    User loadUserByEmail(String email);

    boolean requestPasswordReset(String email) throws Exception;

    boolean resetPassword(String token, String password);

    boolean resetPasswordMail(String email, String token) throws UnsupportedEncodingException, MessagingException;

    //public List<User> getAllUsers();
    //public User save(User utilisateur,Long idRole);
    //public User update(User utilisateur);
    //public void delete(Long code);
    //public User getUserById(Long id );
    //public void assignRolesToUser(Long idUser ,Long idRole );
    //int findByUserRoleAdmin();
    //int findByUserRoleEmployee();
    //int findByUserRoleManager();
    List<String> getAllUserEmails();

}
