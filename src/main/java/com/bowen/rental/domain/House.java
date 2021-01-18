package com.bowen.rental.domain;

import java.io.Serializable;

public class House implements Serializable {
    private int rid;//room id
    private String rname;// room name
    private double price;
    private String houseIntroduce;
    private String address;
    private int count;
    private int sid;
    private String faci;
    private String region;
    private Seller seller;

    public House() {
    }
    public void House(){

    }
    /**
     * @param rid
     * @param rname
     * @param price
     * @param houseIntroduce
     * @param count
     * @param sid
     * @param faci
     * @param address
     * @param region
     */
    public House(int rid, String rname, double price, String houseIntroduce, String address, int count, int sid, String faci, String region) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.houseIntroduce = houseIntroduce;
        this.count = count;
        this.sid = sid;
        this.faci = faci;
        this.address = address;
        this.region=region;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getFaci() {
        return faci;
    }

    public void setFaci(String faci) {
        this.faci = faci;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHouseIntroduce() {
        return houseIntroduce;
    }

    public void setHouseIntroduce(String houseIntroduceIntroduce) {
        this.houseIntroduce = houseIntroduce;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}

