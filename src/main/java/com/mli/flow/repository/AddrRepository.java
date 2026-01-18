package com.mli.flow.repository;

import com.mli.flow.entity.AddrEntity;
import com.mli.flow.uniquekey.AddrKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddrRepository extends JpaRepository<AddrEntity, AddrKey> {
}
