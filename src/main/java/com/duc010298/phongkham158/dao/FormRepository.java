package com.duc010298.phongkham158.dao;

import com.duc010298.phongkham158.entity.FormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FormRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FormEntity> getForm() {
        return jdbcTemplate.query("SELECT ID, Name FROM UltraSoundResult ORDER BY OrderNumber", getRowMapper());
    }

    public String getFormContent(String id) throws Exception {
        String sql = "SELECT Content FROM UltraSoundResult WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
    }

    public String getFormName(String id) {
        String sql = "SELECT Name FROM UltraSoundResult WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
    }

    public boolean addForm(String name, String content) {
        String sqlGetMaxOrderNumber = "SELECT MAX(OrderNumber) FROM UltraSoundResult";
        int maxOrderNumber;
        try {
            maxOrderNumber = jdbcTemplate.queryForObject(sqlGetMaxOrderNumber, Integer.class);
            maxOrderNumber++;
        } catch(NullPointerException ex) {
            maxOrderNumber = 0;
        }
        String sqlAdd = "INSERT INTO UltraSoundResult VALUES (null, ?, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sqlAdd, name, maxOrderNumber, content);
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteForm(String id) {
        String sql = "DELETE FROM UltraSoundResult WHERE ID = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateForm(String id, String content) {
        String sql = "UPDATE UltraSoundResult SET Content = ? WHERE ID = ?";
        try {
            int rows = jdbcTemplate.update(sql, content, id);
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    private RowMapper<FormEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(FormEntity.class);
    }
}
