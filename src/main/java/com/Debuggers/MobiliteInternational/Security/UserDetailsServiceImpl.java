package com.Debuggers.MobiliteInternational.Security;

import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
        User utilisateur = userRepository.findUserByEmail(email);
        return UserDetailsImpl.build(utilisateur);
    } catch (Exception e){
        return (UserDetails) new UsernameNotFoundException("Utilisateur Not Found with email: " + email);
    }
    }
}
