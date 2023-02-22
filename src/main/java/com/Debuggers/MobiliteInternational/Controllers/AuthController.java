package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Request.LoginRequest;
import com.Debuggers.MobiliteInternational.Response.JwtResponse;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

  /*  AuthenticationManager authenticationManager;
    UserRepository utilisateurRepository;
    RoleRepository roleRepository;
    UserService utilisateurService;
    PasswordEncoder encoder;

    public AuthController(AuthenticationManager authenticationManager, UserRepository utilisateurRepository, RoleRepository roleRepository, UserService utilisateurService, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.utilisateurService = utilisateurService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        User utilisateur = utilisateurService.findOne(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt,
                utilisateur.getEmail(),
                roles));

    }

   */
}
