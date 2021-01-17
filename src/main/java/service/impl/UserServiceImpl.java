package com.bowen.rental.service.impl;

import com.bowen.rental.dao.UserDao;
import com.bowen.rental.dao.impl.UserDaoImpl;
import com.bowen.rental.domain.User;
import com.bowen.rental.service.UserService;
import com.bowen.rental.util.MailUtils;
import com.bowen.rental.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * Register
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //Search user by username
        User u = userDao.findByUsername(user.getUsername());
        if(u != null){
            //username exist, failed
            return false;
        }
        //save user
        //set unique active code
        user.setCode(UuidUtil.getUuid());
        //set active status
        user.setStatus("N");
        userDao.save(user);

        //send activate emaill

        String content="<a href='http://localhost/sublet/user/active?code="+user.getCode()+"'>click link to activate account</a>";

        MailUtils.sendMail(user.getEmail(),content,"Activate your account");

        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //search user by active code
        User user = userDao.findByCode(code);
        if(user != null){
            //modify status
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }



    }

    /**
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

}
