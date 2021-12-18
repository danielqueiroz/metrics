package com.metrics.stats.infra.rest;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.domain.service.ParameterService;
import com.metrics.stats.infra.rest.dto.MachineDTO;
import com.metrics.stats.infra.rest.dto.ParametersDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Parameter", description = "Parameter's API")
@RestController
@RequestMapping("/v1/parameters")
@Validated
public class ParametersController {

    private ParameterService parameterService;

    public ParametersController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(description = "ACCEPTED", responseCode = "202"),
            @ApiResponse(description = "BAD REQUEST", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@RequestBody @Valid ParametersDTO parametersDTO)  {

        Parameter.Builder builder = new Parameter.Builder();
        builder.setMachineKey(parametersDTO.getMachineKey());
        builder.setParameters(parametersDTO.getParameters());
        builder.setReceivedTime(LocalDateTime.now());
        parameterService.insert(builder.build());
    }
}
