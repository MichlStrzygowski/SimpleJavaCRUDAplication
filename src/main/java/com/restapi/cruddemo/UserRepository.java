package com.restapi.cruddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll(){
        return jdbcTemplate.query("select * from user", BeanPropertyRowMapper.newInstance(User.class));

    }

    public User getUserById(int id){
        return jdbcTemplate.queryForObject("select * from user where id=?", BeanPropertyRowMapper.newInstance(User.class), id);

    }

    public int addUser(List<User> users) {
        users.forEach(movie -> {
            jdbcTemplate.update("insert into users (name, email, phone) values (?, ?, ?)", movie.getName(), movie.getEmail(), movie.getPhone());
        });
        return 1;
    }

    public void updateUser(int id, User user) {
        jdbcTemplate.update("update users set name=?, email=?, phone=? where id=?", user.getName(), user.getEmail(), user.getPhone(), id);
    }
}
