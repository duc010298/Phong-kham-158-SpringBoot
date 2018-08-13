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

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<FormEntity> getFormContent(Serializable id) {
        String sql = "SELECT Class1, Class2 FROM UltraSoundResult_Content WHERE UltraSoundResultId = ? ORDER BY OrderNumber";
        return jdbcTemplate.query(sql, getRowMapper(), id);
    }

    public String getFormId(String id) {
        String sql = "SELECT FormID FROM UltraSoundResult WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
    }

    private RowMapper<FormEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(FormEntity.class);
    }
}
