package com.metrics.stats.steps;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.repository.MachineRepository;
import com.metrics.stats.infra.rest.dto.MachineDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.Optional;

public class MachineSteps extends CommonSteps {

    private MachineDTO machineDTO;

    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Given("the machine db has no data")
    public void clear() {
        jdbcTemplate.update("delete from machine");
    }

    @Given("the client wants to register the machine {string}")
    public void theClientWantsRegisterMachine(String machineData) {
        String[] machineInfo = machineData.split(":");
        this.machineDTO = new MachineDTO(machineInfo[0], machineInfo[1]);
    }

    @When("the client sends a POST to the machines end point")
    public void clientSendsAPostToEndPoint() {
        postRequest("/v1/machines", Collections.singletonList(machineDTO));
    }

    @Then("the application has inserted the new machine into the database")
    public void theApplicationHasInsertedTheNewMachine() {
        Optional<Machine> machineOptional = machineRepository.findById(machineDTO.getKey());
        Assert.assertTrue(machineOptional.isPresent());
        Machine machine = machineOptional.get();
        Assert.assertEquals(machineDTO.getKey(),machine.getId());
        Assert.assertEquals(machineDTO.getName(),machine.getName());
    }

    @And("the response status code is {int}")
    public void andTheStatusCodeIs(int statusCode) {
        Assert.assertEquals(HttpStatus.valueOf(statusCode), response.getStatusCode());
    }

    @Then("the machines inserted are")
    public void theParametersInsertedAre(DataTable dataTable) {
        dataTable.asMaps().forEach(map -> {
            String sql = "select count(1) from machine where id=? and name=?";
            Assert.assertEquals(Integer.valueOf(1),
                    jdbcTemplate.queryForObject(sql, Integer.class, map.get("id"),
                            map.get("name")));
        });
    }
}
