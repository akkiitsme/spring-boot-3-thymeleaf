package com.quizapp.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(UserBean user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("user: "+user);
        user.setRole(Role.User);
        userDao.save(user);
    }
}
