package com.bowen.rental.service.impl;

import com.bowen.rental.dao.impl.FavoriteDaoImpl;
import com.bowen.rental.dao.HouseDao;
import com.bowen.rental.dao.impl.HouseDaoImpl;
import com.bowen.rental.dao.impl.SellerDaoImpl;
import com.bowen.rental.domain.House;
import com.bowen.rental.domain.Seller;
import com.bowen.rental.service.HouseService;
import com.bowen.rental.dao.FavoriteDao;
import com.bowen.rental.dao.SellerDao;

public class HouseServiceImpl implements HouseService {
    private HouseDao houseDao = new HouseDaoImpl();

    private SellerDao sellerDao = new SellerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public House findOne(String rid) {
        //search object by its id
        House house = houseDao.findOne(Integer.parseInt(rid));
        //get seller by sid
        Seller seller = sellerDao.findById(house.getSid());
        house.setSeller(seller);

        //search how many times people liked the room
        int count = favoriteDao.findCountByRid(house.getRid());
        house.setCount(count);


        return house;
    }
}
