package com.Debuggers.MobiliteInternational.Security;

import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    @ResponseBody
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.loadUserByEmail(userName);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }else{
            System.out.println("User found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getNom().name()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),user.isEnabled(), true, true, true, authorities); }

}
