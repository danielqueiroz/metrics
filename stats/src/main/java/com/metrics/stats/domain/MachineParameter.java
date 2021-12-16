package com.metrics.stats.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document
public class MachineParameter {

    @Id
    private MachineParameterId id;
    private Long value;

    public static class MachineParameterId implements Serializable {
        private String machineId;
        private String parameter;
        private LocalDateTime dateTime;

        public String getMachineId() {
            return machineId;
        }

        public void setMachineId(String machineId) {
            this.machineId = machineId;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }
    }

    /*
    - Provide an endpoint that inserts machine parameters data into the datastore with the following format:

    Request body:
    [source, json]
    ----
    {  "machineKey": "embosser", "parameters": {"core_diameter": 3, "speed": 20 }}
     */
}
