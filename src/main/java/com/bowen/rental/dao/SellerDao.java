package com.bowen.rental.dao;

import com.bowen.rental.domain.Seller;

public interface SellerDao {
    /**
     * @param id
     * @return
     */
    public Seller findById(int id);
}
