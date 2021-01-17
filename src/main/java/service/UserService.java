package com.bowen.rental.service;

import com.bowen.rental.domain.User;

public interface UserService {

    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
