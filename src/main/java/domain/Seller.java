package com.bowen.rental.domain;

import java.io.Serializable;
public class Seller implements Serializable {
    private int sid;
    private String address;

    public Seller(){}

    /**
     * @param sid

     * @param address
     */
    public Seller(int sid, String address) {
        this.sid = sid;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
