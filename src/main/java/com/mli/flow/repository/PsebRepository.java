package com.mli.flow.repository;

import com.mli.flow.entity.PsebEntity;
import com.mli.flow.uniquekey.PsebKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsebRepository extends JpaRepository<PsebEntity, PsebKey> {
}
