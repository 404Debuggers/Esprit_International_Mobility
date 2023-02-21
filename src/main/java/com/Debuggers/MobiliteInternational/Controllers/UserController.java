package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class UserController {
    UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @PostMapping("/users/{idRole}")
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable("idRole") Long idRole){
        return userService.save(user,idRole);

    }


    @PutMapping("/users/{userId}")
    @ResponseBody
    public User updateUser(@PathVariable Long userId, @RequestBody User user){
        return userService.update(user);
    }


    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }


}
