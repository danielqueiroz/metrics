package com.metrics.stats.domain.repository;

import com.metrics.stats.domain.MachineParameter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MachineParameterRepository extends MongoRepository<MachineParameter, MachineParameter.MachineParameterId> {
}
