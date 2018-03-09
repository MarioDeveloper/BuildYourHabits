package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.dao.UserDAO;
import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Transactional
    public User registerNewUserAccount(UserDto registerUser) {

        if(emailExist(registerUser.getEmail())) {
            return null;
        }

        User user = new User();
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setPassword(registerUser.getPassword());
        user.setUsername(registerUser.getEmail());
        user.setExperience(0);
        user.setLife(100);

        user = userDAO.saveUser(user);

        return user;

    }

    @Transactional
    public boolean emailExist(String email) {
        User user = userDAO.findByEmail(email);
        if(user != null) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        List<GrantedAuthority> authority= new ArrayList<GrantedAuthority>();

        authority.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authority);
    }
}
