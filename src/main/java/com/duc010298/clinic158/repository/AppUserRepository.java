package com.duc010298.clinic158.repository;

import com.duc010298.clinic158.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {

    AppUserEntity findByUserName(String userName);
}
