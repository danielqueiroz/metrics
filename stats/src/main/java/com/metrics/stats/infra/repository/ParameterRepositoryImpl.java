package com.metrics.stats.infra.repository;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.repository.ParameterRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ParameterRepositoryImpl implements ParameterRepository {

    private JdbcTemplate jdbcTemplate;

    public ParameterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(List<Parameter> parameterList) {
        String sql = "insert into parameter (machine_id, name, value, reported_time) values (?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Parameter parameter = parameterList.get(i);
                ps.setString(1, parameter.getMachineId());
                ps.setString(2, parameter.getName());
                ps.setLong(3, parameter.getValue());
                ps.setTimestamp(4, Timestamp.valueOf(parameter.getReportedTime()));
            }

            @Override
            public int getBatchSize() {
                return parameterList.size();
            }
        });
    }
}
