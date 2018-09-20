package com.duc010298.phongkham158.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppRoleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AppRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getRoleNames(Long userId) {
        String sql = "Select r.role_name from user_role ur, app_role r where ur.role_id = r.role_id and ur.user_id = ?";
        return jdbcTemplate.queryForList(sql, String.class, userId);
    }
}

