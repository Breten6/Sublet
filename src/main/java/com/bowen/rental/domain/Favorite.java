package com.bowen.rental.domain;

import java.io.Serializable;


public class Favorite implements Serializable {
    private House house;
    private String date;
    private User user;

    public Favorite() {
    }

    /**
     * @param house
     * @param date
     * @param user
     */
    public Favorite(House house, String date, User user) {
            this.house = house;
            this.date = date;
            this.user = user;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
