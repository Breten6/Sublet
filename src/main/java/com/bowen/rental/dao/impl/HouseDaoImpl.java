package com.bowen.rental.dao.impl;

import com.bowen.rental.dao.HouseDao;
import com.bowen.rental.domain.House;
import com.bowen.rental.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());



    @Override
    public House findOne(int rid) {
        String sql = "select * from tab_house where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<House>(House.class),rid);
    }}

