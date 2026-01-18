package com.mli.flow.repository;

import com.mli.flow.entity.PoclEntity;
import com.mli.flow.uniquekey.PoclKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoclRepository extends JpaRepository<PoclEntity, PoclKey> {
}
