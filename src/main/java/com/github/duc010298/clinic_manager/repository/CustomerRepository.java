package com.github.duc010298.clinic_manager.repository;

import com.github.duc010298.clinic_manager.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
