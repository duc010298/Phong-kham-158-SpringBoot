package com.duc010298.clinic158.repository;

import com.duc010298.clinic158.entity.ReportFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportFormRepository extends JpaRepository<ReportFormEntity, Integer> {

    List<ReportFormEntity> findAllByOrderByOrderNumberAsc();

    ReportFormEntity findById(int id);

    @Query("SELECT MAX(rf.orderNumber) FROM ReportFormEntity rf")
    Integer getMaxOrderNumber();

}
