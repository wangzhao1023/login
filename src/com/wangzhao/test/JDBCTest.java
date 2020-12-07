package com.wangzhao.test;

import com.wangzhao.dao.UserDao;
import com.wangzhao.domain.User;
import org.junit.Test;

import java.sql.SQLException;

public class JDBCTest {

    @Test
    public void getUser() throws SQLException {
        User loginuser = new User();
        loginuser.setUsername("zhangsan");
        loginuser.setPassword("123");
        UserDao dao = new UserDao();

        User user = dao.login(loginuser);

        System.out.println(user);


    }
}