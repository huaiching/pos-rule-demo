package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.PsebEntity;
import com.mli.flow.model.entity.uniquekey.PsebKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsebRepository extends JpaRepository<PsebEntity, PsebKey> {
}
