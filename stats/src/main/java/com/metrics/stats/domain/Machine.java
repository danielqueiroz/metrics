package com.metrics.stats.domain;

import com.metrics.stats.domain.exception.RequiredValueException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Machine {

    @Id
    private final String id;
    private final String name;

    public Machine(String id, String name) throws RequiredValueException {

        if(StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
            throw new RequiredValueException("Id and Name can't be null");
        }

        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
