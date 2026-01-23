package com.mli.flow.repository;

import com.mli.flow.entity.PldfEntity;
import com.mli.flow.uniquekey.PldfKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PldfRepository extends JpaRepository<PldfEntity, PldfKey> {
}
