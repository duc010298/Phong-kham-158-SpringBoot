package com.duc010298.phongkham158.dao;

import com.duc010298.phongkham158.entity.AppRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    public List<AppRoleEntity> getAllRole() {
        String sql = "select role_id, role_name from app_role";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    public Boolean addRoleToUser(String roleId, String userId) {
        String sql = "insert into user_role (id, user_id, role_id) values (null, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sql, userId, roleId);
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public Boolean deleteRoleofUser(String userId) {
        String sql = "delete from user_role where user_id=?";
        try {
            jdbcTemplate.update(sql, userId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private RowMapper<AppRoleEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(AppRoleEntity.class);
    }
}

