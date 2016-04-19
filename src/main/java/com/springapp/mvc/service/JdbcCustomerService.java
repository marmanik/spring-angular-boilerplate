package com.springapp.mvc.service;

import com.springapp.mvc.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcCustomerService implements CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(String name) {

        String sql = "INSERT INTO CUSTOMER (NAME) VALUES (?)";

        jdbcTemplate.update(sql, name);
    }

    @Override
    public List<String> findByName(String name) {

        String sql = "SELECT * FROM CUSTOMER WHERE NAME = ?";

        List<String> result = jdbcTemplate.queryForList(sql, String.class, name);

        return result;
    }
}