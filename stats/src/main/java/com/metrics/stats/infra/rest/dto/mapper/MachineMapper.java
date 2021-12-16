package com.metrics.stats.infra.rest.dto.mapper;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.infra.rest.dto.MachineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MachineMapper {

    MachineMapper INSTANCE = Mappers.getMapper( MachineMapper.class );

    @Mapping(source = "id", target = "key")
    MachineDTO toDTO(Machine machine);
}
