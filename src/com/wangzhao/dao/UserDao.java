package com.wangzhao.dao;


import com.wangzhao.domain.User;
import com.wangzhao.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中User表的类
 */
public class UserDao {
    //声明JDBCTemplate对象公用
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser){
        //1.编写sql

        try {
            String  sql = "select * from users where username = ? and password = ?";

            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user ;
        } catch (DataAccessException e) {
            return null;
        }

    }
}
