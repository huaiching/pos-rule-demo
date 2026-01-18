package com.mli.flow.repository;

import com.mli.flow.entity.RsdrEntity;
import com.mli.flow.uniquekey.RsdrKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RsdrRepository extends JpaRepository<RsdrEntity, RsdrKey> {
}
