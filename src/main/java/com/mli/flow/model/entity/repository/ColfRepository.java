package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.ColfEntity;
import com.mli.flow.model.entity.uniquekey.ColfKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColfRepository extends JpaRepository<ColfEntity, ColfKey> {
}
