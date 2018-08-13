package com.phongkham.dao;

import com.phongkham.model.NavsideEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NavsideRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<NavsideEntity> getNavSide() {
        return jdbcTemplate.query("SELECT ID, Name FROM UltraSoundResult ORDER BY OrderNumber", getRowMapper());
    }

    private RowMapper<NavsideEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(NavsideEntity.class);
    }
}
