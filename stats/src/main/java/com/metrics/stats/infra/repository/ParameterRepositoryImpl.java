package com.metrics.stats.infra.repository;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.domain.repository.ParameterRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Parameter> findLatestParametersByMachineId(String machineId) {

        String sql =
            "select param1.machine_id, param1.name, param1.value, param1.reported_time " +
            "from parameter param1, " +
                "(select machine_id, name, max(reported_time) as latest_reported_time " +
                "from parameter " +
                "where (? is null or machine_id = ?) " +
                "group by machine_id, name) param2 " +
            "where " +
                "(? is null or param1.machine_id = ?) and " +
                "param1.machine_id = param2.machine_id and " +
                "param1.name = param2.name and " +
                "param1.reported_time = param2. latest_reported_time " +
            "order by param1.machine_id ";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Parameter(rs.getString(1), rs.getString(2),
                rs.getTimestamp(4).toLocalDateTime(), rs.getLong(3)),
                machineId, machineId, machineId, machineId);
    }
}
