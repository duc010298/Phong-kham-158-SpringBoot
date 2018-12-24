package com.duc010298.clinic158.repository;

import com.duc010298.clinic158.entity.AppRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppRoleRepository extends JpaRepository<AppRoleEntity, Long> {

    @Query("SELECT r.roleName FROM AppRoleEntity r JOIN r.appUserEntities u WHERE u.userId = ?1")
    List<String> getRoleNames(Long userId);
}