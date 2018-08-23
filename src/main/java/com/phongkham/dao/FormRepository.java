package com.phongkham.dao;

import com.phongkham.model.FormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class FormRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FormRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FormEntity> getFormContent(Serializable id) {
        String sql = "SELECT Class1, Class2 FROM UltraSoundResult_Content WHERE UltraSoundResultId = ? ORDER BY OrderNumber";
        return jdbcTemplate.query(sql, getRowMapper(), id);
    }

    public String getFormId(String id) {
        String sql = "SELECT FormID FROM UltraSoundResult WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
    }

    public String getMaxUltraSouldResultId() {
        String sql = "SELECT MAX(ID) FROM UltraSoundResult";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public boolean deleteForm(String id) {
        String sql = "DELETE FROM UltraSoundResult_Content WHERE UltraSoundResultId = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows != 0;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateForm(String id, List<FormEntity> formEntities) {
        int count = 0;
        int OrderNumber = 1;
        String sql = "INSERT INTO UltraSoundResult_Content VALUES (?, ?, ?, ?)";
        for(FormEntity formEntity : formEntities) {
            int rows = jdbcTemplate.update(sql, OrderNumber, formEntity.getClass1(), formEntity.getClass2(), id);
            if(rows == 1) count++;
            OrderNumber++;
        }
        return count == formEntities.size();
    }

    private RowMapper<FormEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(FormEntity.class);
    }
}
