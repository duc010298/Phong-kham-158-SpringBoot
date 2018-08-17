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
public class CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    public List<CustomerEntity> searchCustomer(String name, String YOB, String addressCus, Date dayVisit) {
        String sql = "SELECT Id, DayVisit, Name, YOB, AddressCus, ExpectedDOB, Result, Note FROM Customer " +
                "WHERE Name LIKE ? AND YOB LIKE ? AND AddressCus LIKE ? ";
        if (dayVisit == null) {
            sql += "ORDER BY DayVisit DESC LIMIT 0, 1000";
            return jdbcTemplate.query(sql, getRowMapper(), "%" + name + "%", "%" + YOB + "%", "%" + addressCus + "%");
        } else {
            sql += "AND DayVisit BETWEEN ? AND NOW() ORDER BY DayVisit ASC LIMIT 0, 1000";
            return jdbcTemplate.query(sql, getRowMapper(), "%" + name + "%", "%" + YOB + "%", "%" + addressCus + "%",
                    new java.sql.Date(dayVisit.getTime()));
        }
    }

    public String getReport(int id) {
        String sql = "SELECT Report FROM Customer WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
    }

    private RowMapper<CustomerEntity> getRowMapper() {
        return new BeanPropertyRowMapper<>(CustomerEntity.class);
    }
}