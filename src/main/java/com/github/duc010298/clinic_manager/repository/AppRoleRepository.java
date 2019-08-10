package com.github.duc010298.clinic_manager.repository;

import com.github.duc010298.clinic_manager.entity.AppRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppRoleRepository extends JpaRepository<AppRoleEntity, Integer> {
    @Query("SELECT r.roleName FROM AppRoleEntity r JOIN r.appUserEntities u WHERE u.id = ?1")
    List<String> getRoleNames(int userId);
}
