package com.phongkham.dao;

import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean addCustomer(CustomerEntity customerEntity) {
        String sql = "INSERT INTO Customer VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sql, customerEntity.getName(), customerEntity.getYOB(), customerEntity.getAddressCus(),
                    new java.sql.Date(customerEntity.getDayVisit().getTime()),
                    new java.sql.Date(customerEntity.getExpectedDOB().getTime()),
                    customerEntity.getResult(), customerEntity.getNote(), customerEntity.getReport());
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }
}