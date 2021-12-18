package com.metrics.stats.infra.rest.dto.mapper;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.infra.rest.dto.ParametersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface ParameterMapper {

    ParameterMapper INSTANCE = Mappers.getMapper( ParameterMapper.class );

    default ParametersDTO toDto(List<Parameter> parameters) {
        ParametersDTO params = new ParametersDTO();
        if(parameters != null && !parameters.isEmpty()) {
            params.setMachineKey(parameters.get(0).getMachineId());
            params.setParameters(new HashMap<>());
            for (Parameter param: parameters) {
                params.getParameters().put(param.getName(), param.getValue());
            }
        }
        return params;
    }

    default List<ParametersDTO> toDtos(List<Parameter> parameters) {

        List<ParametersDTO> paramsList = new ArrayList<>();

        if(parameters != null && !parameters.isEmpty()) {

            Map<String, List<Parameter>> machineParams =
                    parameters.stream().collect(Collectors.groupingBy(Parameter::getMachineId));

            machineParams.forEach((key, value) -> {
                ParametersDTO paramsDTO = new ParametersDTO();
                paramsDTO.setMachineKey(key);
                paramsDTO.setParameters(new HashMap<>());
                for (Parameter param: value) {
                    paramsDTO.getParameters().put(param.getName(), param.getValue());
                }
                paramsList.add(paramsDTO);
            });
        }
        return paramsList;
    }
}
