package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.DTO.UserDto;
import com.everydayhabits.product.module.web.entity.User;

public interface UserService {
    User registerNewUserAccount(UserDto registerUser) throws Exception;

    public boolean emailExist(String email);


}