package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.AddrEntity;
import com.mli.flow.model.entity.uniquekey.AddrKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddrRepository extends JpaRepository<AddrEntity, AddrKey> {
}
