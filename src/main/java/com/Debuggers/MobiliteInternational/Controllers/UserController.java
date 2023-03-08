package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@AllArgsConstructor

public class UserController {

/*
=======
>>>>>>> gestion_forum_blog
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
<<<<<<< HEAD
    }*/


    @Autowired
    UserService userService;

    @GetMapping("/emails")
    public List<String> getAllUserEmails() {
        return userService.getAllUserEmails();
    }

}
