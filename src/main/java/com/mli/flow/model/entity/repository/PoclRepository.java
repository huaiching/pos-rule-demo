package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.PoclEntity;
import com.mli.flow.model.entity.uniquekey.PoclKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoclRepository extends JpaRepository<PoclEntity, PoclKey> {
}
