package com.quizapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.matches("^[0-9]*$"))
            return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        else
            return userDao.findByPhoneNo(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
