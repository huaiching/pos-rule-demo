package com.mli.flow.repository;

import com.mli.flow.entity.ClntEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClntRepository extends JpaRepository<ClntEntity, String> {
}
