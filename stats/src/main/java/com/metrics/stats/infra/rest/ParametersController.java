package com.metrics.stats.infra.rest;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.infra.rest.dto.MachineDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Parameter", description = "Parameter's API")
@RestController
@RequestMapping("/v1/parameters")
@Validated
public class ParametersController {


}
