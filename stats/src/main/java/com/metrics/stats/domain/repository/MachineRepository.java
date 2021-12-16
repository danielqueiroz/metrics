package com.metrics.stats.domain.repository;

import com.metrics.stats.domain.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MachineRepository extends MongoRepository<Machine, String> {

    @Query("{id:'?0'}")
    Optional<Machine> findById(String id);
}
