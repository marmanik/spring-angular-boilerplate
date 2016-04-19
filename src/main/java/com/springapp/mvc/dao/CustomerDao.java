package com.springapp.mvc.dao;

import java.util.List;

public interface CustomerDao {

    void insert(String name);

    List<String> findByName(String name);

}