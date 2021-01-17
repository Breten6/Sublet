package com.bowen.rental.service;

import com.bowen.rental.domain.House;

public interface HouseService {
    /**
     * search room by its id
     * @param rid
     * @return
     */
    public House findOne(String rid);
}