package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.ClntEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClntRepository extends JpaRepository<ClntEntity, String> {
}
