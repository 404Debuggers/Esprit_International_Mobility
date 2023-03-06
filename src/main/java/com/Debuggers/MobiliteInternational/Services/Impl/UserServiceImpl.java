package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

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
    public void assignRolesToUser(Long idUser, Long idRole) {
        User user = userRepository.findById(idUser).get();
        Role role = roleRepository.findById(idRole).get();

        user.setRole(role);
        userRepository.save(user);
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
