package com.metrics.stats.infra.rest;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.domain.repository.ParameterRepository;
import com.metrics.stats.domain.service.MachineService;
import com.metrics.stats.domain.service.ParameterService;
import com.metrics.stats.infra.rest.dto.MachineDTO;
import com.metrics.stats.infra.rest.dto.ParametersDTO;
import com.metrics.stats.infra.rest.dto.mapper.MachineMapper;
import com.metrics.stats.infra.rest.dto.mapper.ParameterMapper;
import com.metrics.stats.infra.rest.helper.MachineCSVHelper;
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
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Machine", description = "Machine's API")
@RestController
@RequestMapping("/v1/machines")
@Validated
public class MachineController {

    private final MachineService machineService;
    private final ParameterService parameterService;

    public MachineController(MachineService machineService, ParameterService parameterService) {
        this.machineService = machineService;
        this.parameterService = parameterService;
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(description = "CREATED", responseCode = "201"),
            @ApiResponse(description = "BAD REQUEST", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody List<@Valid MachineDTO> machinesDto)
            throws RequiredValueException {

        List<Machine> machines = new ArrayList<>();
        for (MachineDTO machineDto : machinesDto) {
            Machine machine = new Machine(machineDto.getKey(), machineDto.getName());
            machines.add(machine);
        }
        machineService.insert(machines);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponses({
            @ApiResponse(description = "ACCEPTED", responseCode = "202"),
            @ApiResponse(description = "BAD REQUEST", responseCode = "400"),
            @ApiResponse(description = "INTERNAL SERVER ERROR", responseCode = "500")
    })
    public void upload(@RequestParam("file") MultipartFile file) {

        if(!MachineCSVHelper.isCSVFile(file)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only csv file is allowed");
        }

        try {
            machineService.insert(MachineCSVHelper.parseFile(file.getInputStream()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200"),
            @ApiResponse(description = "NOT FOUND", responseCode = "404")
    })
    @ResponseStatus(HttpStatus.OK)
    public MachineDTO findById(@PathVariable String id) {
        return MachineMapper.INSTANCE.toDTO(machineService.findById(id));
    }

    @GetMapping("/latestParameters")
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<ParametersDTO> findLatestParametersForAllMachines() {
        return ParameterMapper.INSTANCE.toDtos(parameterService.findLatestParametersByMachineId(null));
    }

    @GetMapping("/{id}/latestParameters")
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200")
    })
    @ResponseStatus(HttpStatus.OK)
    public ParametersDTO findLatestParametersByMachineId(@PathVariable String id) {
        return ParameterMapper.INSTANCE.toDto(parameterService.findLatestParametersByMachineId(id));
    }
}
