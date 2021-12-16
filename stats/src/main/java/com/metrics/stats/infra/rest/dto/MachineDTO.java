package com.metrics.stats.infra.rest.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class MachineDTO implements Serializable {

    @NotBlank
    private String key;
    @NotBlank
    private String name;

    public MachineDTO() {}

    public MachineDTO(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }
}
