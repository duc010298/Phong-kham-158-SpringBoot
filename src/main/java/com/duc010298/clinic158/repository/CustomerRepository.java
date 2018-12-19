package com.duc010298.clinic158.repository;

import com.duc010298.clinic158.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query(value = "SELECT customer_name FROM customer WHERE name_search LIKE ?1 GROUP BY customer_name LIMIT 0, 10", nativeQuery = true)
    List<String> searchByName(String value);

    @Query(value = "SELECT YOB FROM customer WHERE YOB LIKE ?1 GROUP BY YOB LIMIT 0, 10", nativeQuery = true)
    List<Integer> searchByYob(String value);

    @Query(value = "SELECT address FROM customer WHERE address_search LIKE ?1 GROUP BY address LIMIT 0, 10", nativeQuery = true)
    List<String> searchByAddress(String value);

}
