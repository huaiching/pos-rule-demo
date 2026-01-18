package com.mli.flow.repository;

import com.mli.flow.entity.RscoEntity;
import com.mli.flow.uniquekey.RscoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RscoRepository extends JpaRepository<RscoEntity, RscoKey> {
}
