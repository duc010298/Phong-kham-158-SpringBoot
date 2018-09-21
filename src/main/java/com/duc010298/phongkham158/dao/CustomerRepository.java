package com.duc010298.phongkham158.dao;

import com.duc010298.phongkham158.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addCustomer(CustomerEntity customerEntity) {
        String sql = "INSERT INTO Customer VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sql, customerEntity.getName(), customerEntity.getNameS(),
                    customerEntity.getYOB(), customerEntity.getAddressCus(), customerEntity.getAddressCusS(),
                    new java.sql.Date(customerEntity.getDayVisit().getTime()),
                    customerEntity.getExpectedDOB() == null ? null : new java.sql.Date(customerEntity.getExpectedDOB().getTime()),
                    customerEntity.getResult(), customerEntity.getNote(), customerEntity.getReport());
            return rows == 1;
        } catch (Exception ex) {
            return false;
        }
    }
}
