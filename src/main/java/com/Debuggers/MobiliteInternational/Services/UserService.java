package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User save(User utilisateur,Long idRole);

    public User update(User utilisateur);
    public void delete(Long code);

    public User getUserById(Long id );


    public void assignRolesToUser(Long idUser ,Long idRole );


    int findByUserRoleAdmin();
    int findByUserRoleEmployee();
    int findByUserRoleManager();
}
