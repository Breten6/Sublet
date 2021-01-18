package com.bowen.rental.service.impl;

import com.bowen.rental.dao.FavoriteDao;
import com.bowen.rental.dao.impl.FavoriteDaoImpl;
import com.bowen.rental.domain.Favorite;
import com.bowen.rental.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
