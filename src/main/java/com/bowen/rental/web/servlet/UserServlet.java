package com.bowen.rental.web.servlet;

import com.bowen.rental.domain.ResultInfo;
import com.bowen.rental.domain.User;
import com.bowen.rental.service.UserService;
import com.bowen.rental.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    /**
     * Register
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //verify
        String check = request.getParameter("check");
        //get verify code form Session
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//Ensure the verify code is unique
        //judge input code
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();
            //fail
            info.setFlag(false);
            info.setErrorMsg("wrong code");
            //trans info to json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //use UserService to finish register
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            //success
            info.setFlag(true);
        }else{
            //false
            info.setFlag(false);
            info.setErrorMsg("Failed");
        }

        //trans info to json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //write json to client
        //set content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    /**
     * log in
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get username and password
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //search by service
        User u  = service.login(user);
        ResultInfo info = new ResultInfo();
        //do we have this account?
        if(u == null){
            //wrong
            info.setFlag(false);
            info.setErrorMsg("wrong username or password");
        }
        //did user activated?
        if(u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("Activate account before log in");
        }
        //success!
        if(u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u);

            //log in
            info.setFlag(true);
        }
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    /**
     * find one user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get log in user by session
        Object user = request.getSession().getAttribute("user");
       writeValue(user,response);
    }

    /**
     * log out
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //delete session
        request.getSession().invalidate();

        //go to login page
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * activate account
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get verify code
        String code = request.getParameter("code");
        if(code != null){
            boolean flag = service.active(code);
            String msg = null;
            if(flag){
                msg = "Success, <a href='login.html'>Login</a>";
            }else{
                //failed
                msg = "failed, pls contact me";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
