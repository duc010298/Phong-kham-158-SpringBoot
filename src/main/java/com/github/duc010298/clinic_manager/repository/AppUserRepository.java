package com.github.duc010298.clinic_manager.repository;

import com.github.duc010298.clinic_manager.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Integer> {
    AppUserEntity findByUserName(String userName);
}