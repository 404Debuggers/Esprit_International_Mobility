package com.Debuggers.MobiliteInternational.Services.Impl;


import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UniversityService;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


/*    UserRepository userRepository;
    RoleRepository roleRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User save(User utilisateur,Long idRole) {
        Role role = roleRepository.findById(idRole).orElse(null);
        utilisateur.setRole(role);
        return userRepository.save(utilisateur);
    }
    @Override
    public User update(User utilisateur) {
        return userRepository.save(utilisateur);
    }
    @Override
    public void delete(Long code) {
        User u = getUserById(code);
=======
    UserRepository userRepository;

    RoleRepository roleRepository;
    @Override
    public List<User> getAllUsers() {
        return
                userRepository.findAll();
    }

    @Override
    public User save(User utilisateur,Long idRole) {


        Role role = roleRepository.findById(idRole).orElse(null);
        utilisateur.setRole(role);
        return userRepository.save(utilisateur);


    }

    @Override
    public User update(User utilisateur) {

        return userRepository.save(utilisateur);
    }

    @Override
    public void delete(Long code) {
        User u = getUserById(code);

>>>>>>> gestion_foyer
        if(u==null)
        {
            System.out.println("not found");
        }
        userRepository.delete(u);
<<<<<<< HEAD
    }
=======


    }

>>>>>>> gestion_foyer
    @Override
    public User getUserById(Long id) {
        User utilisateur = userRepository.findById(id).orElse(null);
        return utilisateur;
    }
<<<<<<< HEAD
=======

>>>>>>> gestion_foyer
    @Override
    public void assignRolesToUser(Long idUser, Long idRole) {
        User user = userRepository.findById(idUser).get();
        Role role = roleRepository.findById(idRole).get();
<<<<<<< HEAD
        user.setRole(role);
        userRepository.save(user);
    }
=======

        user.setRole(role);
        userRepository.save(user);
    }

>>>>>>> gestion_foyer
    @Override
    public int findByUserRoleAdmin() {
        return 0;
    }
<<<<<<< HEAD
=======

>>>>>>> gestion_foyer
    @Override
    public int findByUserRoleEmployee() {
        return 0;
    }
    @Override
    public int findByUserRoleManager() {
        return 0;
    }*/


    @Autowired
    UserRepository userRepository;

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

