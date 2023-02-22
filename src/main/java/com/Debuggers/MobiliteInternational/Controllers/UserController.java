package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
@Autowired
    UserService userService;

    @GetMapping("/emails")
    public List<String> getAllUserEmails() {
        return userService.getAllUserEmails();
    }
}
