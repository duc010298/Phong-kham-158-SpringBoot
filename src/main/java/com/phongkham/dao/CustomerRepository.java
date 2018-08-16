package com.phongkham.dao;

import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean addCustomer(CustomerEntity customerEntity) {
        String sql = "INSERT INTO Customer VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sql, customerEntity.getName(), customerEntity.getYOB(),
                    customerEntity.getAddressCus(), new java.sql.Date(customerEntity.getDayVisit().getTime()),
                    customerEntity.getExpectedDOB() == null ? null : new java.sql.Date(customerEntity.getExpectedDOB().getTime()),
                    customerEntity.getResult(), customerEntity.getNote(), customerEntity.getReport());
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<String> searchByName(String value) {
        String sql = "SELECT Name FROM Customer WHERE Name LIKE ? GROUP BY Name LIMIT 0, 15";
        return jdbcTemplate.queryForList(sql, String.class, "%" + value + "%");
    }

    public List<String> searchByYOB(String value) {
        String sql = "SELECT YOB FROM Customer WHERE YOB LIKE ? GROUP BY YOB LIMIT 0, 15";
        return jdbcTemplate.queryForList(sql, String.class, "%" + value + "%");
    }

    public List<String> searchByAddress(String value) {
        String sql = "SELECT AddressCus FROM Customer WHERE AddressCus LIKE ? GROUP BY AddressCus  LIMIT 0, 15";
        return jdbcTemplate.queryForList(sql, String.class, "%" + value + "%");
    }
}