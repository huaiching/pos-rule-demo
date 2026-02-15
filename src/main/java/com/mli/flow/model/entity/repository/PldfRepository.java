package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.PldfEntity;
import com.mli.flow.model.entity.uniquekey.PldfKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PldfRepository extends JpaRepository<PldfEntity, PldfKey> {
}
