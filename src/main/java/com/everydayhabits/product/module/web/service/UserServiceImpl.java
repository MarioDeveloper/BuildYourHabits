package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.DTO.UserDto;
import com.everydayhabits.product.module.web.dao.UserDAO;
import com.everydayhabits.product.module.web.dao.UserDAOImpl;
import com.everydayhabits.product.module.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        user.setEmail(registerUser.getEmail());
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

}
