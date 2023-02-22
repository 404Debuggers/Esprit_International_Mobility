package com.Debuggers.MobiliteInternational.Validator;

import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator {

    private UserService us;

    public UserValidator(UserService us) {
        this.us = us;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);

    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(user.getEmail());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if ((user.getFirstName().length()) < 6 || (user.getFirstName().length() > 32)) {
            errors.rejectValue("firstName", "Size.user.username");
        }
        if (us.findOne(user.getEmail()) != null) {
            errors.rejectValue("Email", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("passwd", "Size.user.password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enabled", "NotEmpty");
        if (user.isEnabled()!= true) {
            errors.rejectValue("enabled", "enable account!");
        }

        if (matcher.find()==false) {
            errors.rejectValue("emailAddress", "EmailAddress.not.valid");
        }
        if (us.loadUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("emailAddress", "Duplicate.emailAddress");
        }


    }
    }

