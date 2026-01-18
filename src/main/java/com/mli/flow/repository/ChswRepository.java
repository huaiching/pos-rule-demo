package com.mli.flow.repository;

import com.mli.flow.entity.ChswEntity;
import com.mli.flow.uniquekey.ChswKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChswRepository extends JpaRepository<ChswEntity, ChswKey> {
}
