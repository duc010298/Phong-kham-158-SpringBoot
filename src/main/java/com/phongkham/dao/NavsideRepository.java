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

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NavsideRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NavsideEntity> getNavSide() {
        return jdbcTemplate.query("SELECT ID, Name FROM UltraSoundResult ORDER BY OrderNumber", getRowMapper());
    }

    public boolean deleteNavSide(String id) {
        String sql = "DELETE FROM UltraSoundResult WHERE ID = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    private RowMapper<NavsideEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(NavsideEntity.class);
    }
}
