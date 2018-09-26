package com.duc010298.phongkham158.dao;

import com.duc010298.phongkham158.entity.AppUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AppUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppUserEntity findUserAccount(String userName) {
        String sql = "select user_id, user_name, encryted_password from app_user where user_name=?";
        List<AppUserEntity> appUserEntities = jdbcTemplate.query(sql, getRowMapper(), userName);
        if (appUserEntities.isEmpty()) return null;
        return appUserEntities.get(0);
    }

    public Boolean addUser(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);

        String sql = "insert into app_user (user_id, user_name, encryted_password, enabled) values (null, ?, ?, 1)";
        try {
            int rows = jdbcTemplate.update(sql, username, password);
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    private RowMapper<AppUserEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(AppUserEntity.class);
    }
}