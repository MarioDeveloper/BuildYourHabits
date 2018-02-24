package com.everydayhabits.product.module.web.dao;

import com.everydayhabits.product.module.web.entity.Level;
import com.everydayhabits.product.module.web.entity.User;

public interface UserDAO {

    public User saveUser(User theUser);

    User findByEmail(String email);

    User getUserByEmail(String theEmail);

    Level getLevelById(int levelId);

}
