package com.github.duc010298.clinic158.repository;

import com.github.duc010298.clinic158.entity.CustomerHiddenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CustomerHiddenRepository extends JpaRepository<CustomerHiddenEntity, Integer> {

    List<CustomerHiddenEntity> findAllByDayVisitOrderByDayVisit(Date dayVisit);

    @Query("SELECT c.report FROM CustomerHiddenEntity c WHERE c.id = ?1")
    String getReport(Integer id);
}
