package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.RsdrEntity;
import com.mli.flow.model.entity.uniquekey.RsdrKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RsdrRepository extends JpaRepository<RsdrEntity, RsdrKey> {
}
