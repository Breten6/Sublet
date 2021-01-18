package com.bowen.rental.dao;

import com.bowen.rental.domain.Favorite;

public interface FavoriteDao {

    /**
     * search by rid and uid
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * search adding wish list times
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * add to wish list
     * @param i
     * @param uid
     */
    void add(int i, int uid);
}
