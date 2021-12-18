package com.metrics.stats.domain;

import com.metrics.stats.domain.exception.RequiredValueException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Machine {

    @Id
    private final String id;
    private final String name;
    private final List<Parameter> parameters;

    public Machine(String id, String name) throws RequiredValueException {
        this(id, name, null);
    }

    public Machine(String id, String name, List<Parameter> parameters) throws RequiredValueException {
        if(StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
            throw new RequiredValueException("Id and Name can't be null");
        }
        this.id = id;
        this.name = name;
        this.parameters = parameters;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
