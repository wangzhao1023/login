package com.wangzhao.web.servlet;

import com.wangzhao.dao.UserDao;
import com.wangzhao.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码

        req.setCharacterEncoding("utf-8");

//        //2.获取请求参数
//
//        String loginUsername = req.getParameter("username");
//        String loginPassword = req.getParameter("password");
//        //3.封装
//        User loginUser = new User();
//        loginUser.setPassword(loginPassword);
//        loginUser.setUsername(loginUsername);

        //对2,3步骤进行简化

            Map<String, String[]> parameterMap = req.getParameterMap();
            User loginUser = new User();
            try {
                BeanUtils.populate(loginUser,parameterMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
        }

        //4调用login方法

        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        if(user==null){
            //登录失败,将页面转发到failservlet页面,并显示登录失败

            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //登录成功,将页面转发到successServlet页面,并显示欢迎用户名,登录成功
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
