package com.metrics.stats.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrics.stats.domain.repository.MachineRepository;
import com.metrics.stats.infra.rest.dto.ParameterInfoDTO;
import com.metrics.stats.infra.rest.dto.ParametersDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class ParameterSteps extends CommonSteps {

    private ParametersDTO parametersDTO;

    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Given("there is no parameter in the database")
    public void clear() {
        jdbcTemplate.update("delete from parameter");
    }

    @When("the client sends a POST to the parameters end point")
    public void clientSendsAPostToEndPoint() {
        postRequest("/v1/machines", Collections.singletonList(parametersDTO));
    }

    @And("user will use the request body in the file {string}")
    public void useTheRequestBody(String file) throws IOException {
        parametersDTO = (ParametersDTO) readFile("requestBody/" + file + ".json", ParametersDTO.class);
    }

    @When("the user sends the POST request")
    public void sendThePostRequest() {
        postRequest("/v1/parameters", parametersDTO);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Then("the parameters are inserted into the database")
    public void parametersInsertedIntoDataBase() {
        String url = String.format("/v1/machines/%s/latestParameters", parametersDTO.getMachineKey());
        getRequest(url, ParametersDTO.class);
        Assert.assertEquals(200, response.getStatusCodeValue());
        ParametersDTO insertedParametersDTO = (ParametersDTO) response.getBody();
        Assert.assertEquals(parametersDTO, insertedParametersDTO);
    }

    @And("user selects the file {string}")
    public void userSelectsTheFile(String fileName) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("csv/" + fileName);
        file = new File(resource.toURI());
    }

    @When("the user uploads the file to {string}")
    public void userUploadsTheFile(String url) {
        uploadFile(url);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Then("the parameters inserted are")
    public void theParametersInsertedAre(DataTable dataTable) {
        dataTable.asMaps().forEach(map -> {
            String sql = "select count(1) from parameter where machine_id=? and name=? and value= ?";
            Assert.assertEquals(Integer.valueOf(1),
                    jdbcTemplate.queryForObject(sql, Integer.class, map.get("machineId"),
                            map.get("parameterName"), Double.valueOf(map.get("value"))));
        });
    }

    @Then("the latest parameters for machine {string} is {string}")
    public void theLatestParameterForMachineId(String machineId, String fileName) throws IOException {
        String url = String.format("/v1/machines/%s/latestParameters", machineId);
        getRequest(url, ParametersDTO.class);
        Assert.assertEquals(200, response.getStatusCodeValue());
        ParametersDTO insertedParametersDTO = (ParametersDTO) response.getBody();
        ParametersDTO expectedParametersDTO = (ParametersDTO) readFile("requestBody/" + fileName + ".json", ParametersDTO.class);
        Assert.assertEquals(expectedParametersDTO, insertedParametersDTO);
    }

    @Then("the computed info for machine {string} and parameter {string} for last {int} minutes is {string}")
    public void theComputedInfoForMachineAndParameter(String machineId, String paramName, int minutes, String fileName) throws IOException {
        String url = String.format("/v1/parameters/computedInfo?machineId=%s&parameterName=%s&lastMinutes=%s",
                machineId, paramName, minutes);
        getRequest(url, List.class);
        Assert.assertEquals(200, response.getStatusCodeValue());
        List<ParameterInfoDTO> returnedParamsInfo = new ObjectMapper().convertValue(response.getBody(),
                new com.fasterxml.jackson.core.type.TypeReference<List<ParameterInfoDTO>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        List<ParameterInfoDTO> expectedParametersDTO = readListComputedInfo("responses/" + fileName + ".json");
        Assert.assertEquals(expectedParametersDTO, returnedParamsInfo);
    }

    private Object readFile(String fileName, Class clazz) throws IOException {
        try (InputStream inJson = getClass().getClassLoader().getResourceAsStream(fileName)) {
            return new ObjectMapper().readValue(inJson, clazz);
        }
    }

    private List<ParameterInfoDTO> readListComputedInfo(String fileName) throws IOException {
        try (InputStream inJson = getClass().getClassLoader().getResourceAsStream(fileName)) {
            return Arrays.asList(new ObjectMapper().readValue(inJson, ParameterInfoDTO[].class));
        }
    }
}
