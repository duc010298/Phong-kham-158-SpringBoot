package com.github.duc010298.clinic_manager.repository;

import com.github.duc010298.clinic_manager.entity.ClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<ClinicEntity, Integer> {

}