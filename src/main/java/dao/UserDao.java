package com.bowen.rental.dao;

import com.bowen.rental.domain.User;

public interface UserDao {

    /**
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * @param user
     */
    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
