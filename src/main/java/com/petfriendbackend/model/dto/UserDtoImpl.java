package com.petfriendbackend.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.petfriendbackend.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.petfriendbackend.model.User;
import com.petfriendbackend.model.Login;

public class UserDtoImpl implements UserDto {
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void register(User user) {

        String sql = "insert into users values(?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, new Object[] { user.getUserId(), user.getUserName(), user.getEncrytedPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getGender() });
    }

    public User validateUser(Login login) {

        String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
                + "'";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

}


class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();

        user.setUserId(Long.valueOf(rs.getString("userId")));
        user.setUserName(rs.getString("username"));
        user.setEncrytedPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setGender(rs.getString("address"));

        return user;
    }
}
