package com.bowen.rental.service;

public interface FavoriteService {

    /**
     * is favorite?
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(String rid, int uid);

    /**
     * like it!
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
