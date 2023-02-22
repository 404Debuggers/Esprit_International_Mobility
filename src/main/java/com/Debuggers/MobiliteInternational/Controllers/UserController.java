package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.Enum.ERole;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    UserService userService;
    private final UserRepository userRepository;


    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers(){


        return ResponseEntity.ok().body(userService.getAllUsers());
    }


    @PostMapping("/{idRole}")
    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user, @PathVariable("idRole") Long idRole){
        return ResponseEntity.created(null).body( userService.save(user,idRole));

    }


    @PutMapping("/users/{userId}")
    @ResponseBody
    public User updateUser(@PathVariable Long userId, @RequestBody User user){

        user.setUser_Id(userId);

        return userService.update(user);
    }


    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }


    @PostMapping("/users/assigneRoleToUser/{idUser}/{roleName}")
    public ResponseEntity<?>addRoleToUser(@PathVariable("idUser") Long idUser , @PathVariable("roleName")Long roleId)
    {
        userService.assignRoleToUser(idUser, roleId);
        return ResponseEntity.ok().build();
    }


}
