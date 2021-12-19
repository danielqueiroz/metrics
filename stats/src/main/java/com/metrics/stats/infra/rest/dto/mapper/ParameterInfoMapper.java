package com.metrics.stats.infra.rest.dto.mapper;

import com.metrics.stats.domain.ParameterStats;
import com.metrics.stats.infra.rest.dto.ParameterInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ParameterInfoMapper {

    ParameterInfoMapper INSTANCE = Mappers.getMapper( ParameterInfoMapper.class );

    List<ParameterInfoDTO> toDto(List<ParameterStats> parameterStatsList);
}
