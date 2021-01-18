package com.bowen.rental.web.servlet;

import com.bowen.rental.domain.House;
import com.bowen.rental.domain.User;
import com.bowen.rental.service.FavoriteService;
import com.bowen.rental.service.HouseService;
import com.bowen.rental.service.impl.FavoriteServiceImpl;
import com.bowen.rental.service.impl.HouseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/house/*")
public class HouseServlet extends BaseServlet {

    private HouseService houseService = new HouseServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * find a room by id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get id
        String rid = request.getParameter("rid");
        System.out.println("run findone");
        //use service to find room
        House house = houseService.findOne(rid);
        //return by json
        writeValue(house,response);
    }

    /**
     * did user liked it?
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get room id
        String rid = request.getParameter("rid");

        //get the user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //if didn't log in
            uid = 0;
        }else{
            uid = user.getUid();
        }

        //use favourite service to check
        boolean flag = favoriteService.isFavorite(rid, uid);

        //return
        writeValue(flag,response);
    }

    /**
     * like it!
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get room id
        String rid = request.getParameter("rid");
        //get user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            return ;
        }else{
            uid = user.getUid();
        }


        //like it by favourite service
        favoriteService.add(rid,uid);

    }


}