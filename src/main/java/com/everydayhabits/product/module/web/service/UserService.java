package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User registerNewUserAccount(UserDto registerUser);

    boolean emailExist(String email);

}
