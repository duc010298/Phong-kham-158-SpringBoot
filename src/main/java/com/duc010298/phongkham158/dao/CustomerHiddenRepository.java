package com.duc010298.phongkham158.dao;

import com.duc010298.phongkham158.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
