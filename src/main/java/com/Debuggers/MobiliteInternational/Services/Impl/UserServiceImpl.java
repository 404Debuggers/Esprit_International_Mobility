package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;

    private  PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users : ");
        return userRepository.findAll();
    }

    @Override
    public User save(User utilisateur,Long idRole) {

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
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void assignRoleToUser(Long idUser,Long roleId) {

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
        return userRepository.findUserByEmail(email);
    }
    @Override
    public int findByUserRoleAdmin() {
        return 0;
    }

    @Override
    public int findByUserRoleEmployee() {
        return 0;
    }

    @Override
    public int findByUserRoleManager() {
        return 0;
    }
}
