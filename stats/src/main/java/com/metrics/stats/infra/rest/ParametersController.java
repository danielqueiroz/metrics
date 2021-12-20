package com.metrics.stats.infra.rest;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.service.ParameterService;
import com.metrics.stats.infra.rest.dto.ParameterInfoDTO;
import com.metrics.stats.infra.rest.dto.ParametersDTO;
import com.metrics.stats.infra.rest.dto.mapper.ParameterInfoMapper;
import com.metrics.stats.infra.rest.helper.MachineCSVHelper;
import com.metrics.stats.infra.rest.helper.ParameterCSVHelper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            @ApiResponse(description = "OK", responseCode = "200"),
            @ApiResponse(description = "BAD REQUEST", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Valid ParametersDTO parametersDTO)  {

        Parameter.Builder builder = new Parameter.Builder();
        builder.setMachineKey(parametersDTO.getMachineKey());
        builder.setParameters(parametersDTO.getParameters());
        builder.setReceivedTime(LocalDateTime.now());
        parameterService.insert(builder.build());
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200"),
            @ApiResponse(description = "BAD REQUEST", responseCode = "400"),
            @ApiResponse(description = "INTERNAL SERVER ERROR", responseCode = "500")
    })
    public void upload(@RequestParam("file") MultipartFile file) {

        if(!ParameterCSVHelper.isCSVFile(file)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only csv file is allowed");
        }

        try {
            parameterService.insert(ParameterCSVHelper.parseFile(file.getInputStream()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/computedInfo")
    @ResponseStatus(HttpStatus.OK)
    public List<ParameterInfoDTO> findParametersInfo(@RequestParam(required = false) String machineId,
                                                     @RequestParam(required = false) String parameterName,
                                                     @RequestParam Integer lastMinutes) {
        LocalDateTime dateTime = LocalDateTime.now().minus(lastMinutes, ChronoUnit.MINUTES);
        return ParameterInfoMapper.INSTANCE.toDto(parameterService.findComputedParameterInfo(machineId, parameterName, dateTime));
    }
}
