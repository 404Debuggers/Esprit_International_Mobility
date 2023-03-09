package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.DTO.PasswordResetRequestModel;
import com.Debuggers.MobiliteInternational.Entity.Enum.ERole;
import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Request.LoginRequest;
import com.Debuggers.MobiliteInternational.Request.SignupRequest;
import com.Debuggers.MobiliteInternational.Response.JwtResponse;
import com.Debuggers.MobiliteInternational.Response.MessageResponse;
import com.Debuggers.MobiliteInternational.Security.Jwt.JwtUtil;
import com.Debuggers.MobiliteInternational.Security.Services.IMailService;
import com.Debuggers.MobiliteInternational.Security.Services.UserDetailsImpl;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    UserService userService;




    @Autowired
    IMailService mailService;





    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println(jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        //  User utilisateur = userService.findOne(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account

        // Generate a verification token
        String token = UUID.randomUUID().toString();
        User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getDateNaissance(),signUpRequest.getPhone(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getAdresse());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByNom(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByNom(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "PARTNER":
                        Role modRole = roleRepository.findByNom(ERole.ROLE_PARTNER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "STUDENT":
                    default:
                        Role userRole = roleRepository.findByNom(ERole.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setToken(token); // Save the verification token with the user details

        userRepository.save(user);

        // Send the verification email
        String subject = "Verify your email address";
        String confirmationUrl = "http://localhost:8080/verify-email?token=" + token; // Replace with your verification URL
        String message = "Please click the link below to verify your email address:\n" + confirmationUrl;
        mailService.sendEmail(user.getEmail(), subject, message);

        user.setEnabled(true);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @CrossOrigin(origins = "*")
    @PostMapping("/password-reset-request")

    public boolean requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) throws Exception {

        boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

        if (operationResult) {
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/password-reset")
    public boolean resetPassword(@RequestBody PasswordResetRequestModel passwordResetModel) {

        return userService.resetPassword(passwordResetModel.getToken(), passwordResetModel.getPassword());
    }



}