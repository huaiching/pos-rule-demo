package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.ChswEntity;
import com.mli.flow.model.entity.uniquekey.ChswKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChswRepository extends JpaRepository<ChswEntity, ChswKey> {
}
