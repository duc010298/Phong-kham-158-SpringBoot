package com.github.duc010298.clinic_manager.repository;

import com.github.duc010298.clinic_manager.entity.ClinicEntity;
import com.github.duc010298.clinic_manager.entity.ReportFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportFormRepository extends JpaRepository<ReportFormEntity, UUID> {

    List<ReportFormEntity> findAllByOrderByOrderNumberAsc();

    List<ReportFormEntity> findAllByClinicByClinicIdOrderByOrderNumberAsc(ClinicEntity clinicEntity);

    Optional<ReportFormEntity> findById(UUID id);
//
//    @Query("SELECT MAX(rf.orderNumber) FROM ReportFormEntity rf")
//    Integer getMaxOrderNumber();
}
