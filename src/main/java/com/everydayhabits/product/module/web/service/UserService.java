package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.DTO.UserDto;
import com.everydayhabits.product.module.web.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User registerNewUserAccount(UserDto registerUser);

    public boolean emailExist(String email);

}
