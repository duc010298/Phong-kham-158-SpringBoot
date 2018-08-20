package com.phongkham.dao;

import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CustomerHiddenRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerHiddenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCustomer(CustomerEntity customerEntity) {
        String sql = "INSERT INTO Customer_Hidden VALUES(null, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customerEntity.getName(), customerEntity.getYOB(), customerEntity.getAddressCus(),
                customerEntity.getDayVisit(), customerEntity.getResult(), customerEntity.getReport());
    }

    public List<CustomerEntity> searchCustomer(Date dayVisit) {
        String sql = "SELECT Id, Name, YOB, AddressCus, Result FROM Customer_Hidden WHERE DayVisit = ? ORDER BY DayVisit";
        return jdbcTemplate.query(sql, getRowMapper(), new java.sql.Date(dayVisit.getTime()));
    }

    public String getReport(int id) {
        String sql = "SELECT Report FROM Customer_Hidden WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
    }


    private RowMapper<CustomerEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(CustomerEntity.class);
    }
}
