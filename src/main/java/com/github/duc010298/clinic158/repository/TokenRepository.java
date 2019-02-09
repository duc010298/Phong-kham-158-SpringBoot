package com.github.duc010298.clinic158.repository;

import com.github.duc010298.clinic158.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    TokenEntity findById(int id);
}
