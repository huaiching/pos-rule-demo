package com.mli.flow.repository;

import com.mli.flow.entity.ColfEntity;
import com.mli.flow.uniquekey.ColfKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColfRepository extends JpaRepository<ColfEntity, ColfKey> {
}
