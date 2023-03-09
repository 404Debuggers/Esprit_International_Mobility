package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.PasswordResetTokenEntity;
import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.PasswordResetTokenRepository;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Security.Jwt.JwtUtil;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@AllArgsConstructor


@Service

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;


    PasswordEncoder passwordEncoder;

    PasswordResetTokenRepository passwordResetTokenRepository;

    JwtUtil utils;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users : ");
        return userRepository.findAll();
    }

    @Override
    public User save(User utilisateur, Long idRole) {

        Role role = roleRepository.findById(idRole).get();

        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.getRoles().add(role);
        return userRepository.save(utilisateur);


    }

    @Override
    public User update(User utilisateur) {

        User existingUser = userRepository.findById(utilisateur.getUser_Id()).orElse(null);

        if(utilisateur.getFirstName() != null ) {
            existingUser.setFirstName(utilisateur.getFirstName());
        }
        if(utilisateur.getLastName() != null ) {
            existingUser.setLastName(utilisateur.getLastName());
        }
        if(utilisateur.getPhone() != null ) {
            existingUser.setPhone(utilisateur.getPhone());
        }
        if(utilisateur.getImage() != null ) {
            existingUser.setImage(utilisateur.getImage());
        }
        if(utilisateur.getDateNaissance()!= null ) {
            existingUser.setDateNaissance(utilisateur.getDateNaissance());
        }
        if(utilisateur.getAdresse() != null ) {
            existingUser.setAdresse(utilisateur.getAdresse());

        }

        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long code) {
        User u = getUserById(code);

        if(u==null)
        {
            System.out.println("not found");
        }
        userRepository.delete(u);


    }

    @Override
    public User getUserById(Long id) {
        User utilisateur = userRepository.findById(id).orElse(null);
        return utilisateur;
    }

    @Override
    public User findOne(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Error : Email is not found"));
    }

    @Override
    public void assignRoleToUser(Long idUser, Long roleId) {

        log.info("Adding role {} to a user : " ,idUser,roleId);
        User user = userRepository.findById(idUser).get();
        if(user == null )
        {
            log.info("user Not found");
        }
        Role role = roleRepository.findById(roleId).get();

        user.getRoles().add(role);
        userRepository.save(user);
    }


    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Error : Email is not found"));
    }


    @Override
    public boolean requestPasswordReset(String email) throws Exception{
        boolean returnValue = false;
        User userEntity = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Error : Email is not found"));

        if(userEntity==null) {
            return returnValue;
        }

        String token =  utils.generateEmailVerificationToken(userEntity.getEmail());

        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);

        returnValue = resetPasswordMail (userEntity.getEmail(),token);
        return returnValue;
    }

    @Override
    public boolean resetPassword(String token, String password) {
        boolean returnValue = false;

        PasswordResetTokenEntity passwordResetTokenEntity =passwordResetTokenRepository.findByToken(token);

        System.out.println(token);
        if(JwtUtil.hastokenExpired2(token)){
            passwordResetTokenRepository.delete(passwordResetTokenEntity);
            return returnValue;
        }



        if(passwordResetTokenEntity==null){
            return returnValue;
        }

        //prepare new password
        String encodedPassword = passwordEncoder.encode(password);

        // Update User password in database
        User userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setPassword(encodedPassword);
        User savedUserEntity = userRepository.save(userEntity);

        // verify if user password was saved successfully

        if(savedUserEntity!= null && savedUserEntity.getPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue= true;
        }

        // Remove Password Reset token from database
        passwordResetTokenRepository.delete(passwordResetTokenEntity);
        return returnValue;

    }


    @Override
    public boolean resetPasswordMail(String email, String token) throws UnsupportedEncodingException, MessagingException {
        String subject = "Request for reset password";
        String senderName = "404Debuggers Mobility International platform";

        String mailContent = "<p> Someone has requested to reset your password with our project .If it were not you , please ignore it otherwise please click on the link below : </p>";
        String verifyURL = "http://localhost:4200/forget-password?token=" + token;

        mailContent += "<h2><a href=" + verifyURL + ">Click this link to reset password</a></h2>";

        mailContent += "<p> thank you<br> 404 Debuggers team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("btravel020@gmail.com", senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
        if(message !=null &&(message.getMessageID()!=null && !message.getMessageID().isEmpty())){
            System.out.println("email sent");
            return true;
        }

        return false;

    }







    @Override
    public List<String> getAllUserEmails() {
        List<User> users = userRepository.findAll();
        List<String> emails = new ArrayList<>();
        for (User user : users) {
            emails.add(user.getEmail());
        }
        return emails;

    }
}