package com.mli.flow.model.entity.repository;

import com.mli.flow.model.entity.entity.RscoEntity;
import com.mli.flow.model.entity.uniquekey.RscoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RscoRepository extends JpaRepository<RscoEntity, RscoKey> {
}
