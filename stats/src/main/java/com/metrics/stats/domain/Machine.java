package com.metrics.stats.domain;

import com.metrics.stats.domain.exception.RequiredValueException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
public class Machine {

    @Id
    private final String id;
    private final String name;
    private final LocalDateTime localDateTime;

    public Machine(String id, String name, LocalDateTime localDateTime) throws RequiredValueException {

        if(StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
            throw new RequiredValueException("Id and Name can't be null");
        }

        this.id = id;
        this.name = name;
        this.localDateTime = Objects.requireNonNullElseGet(localDateTime, LocalDateTime::now);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
