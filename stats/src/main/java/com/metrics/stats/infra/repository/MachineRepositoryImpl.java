package com.metrics.stats.infra.repository;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.domain.repository.MachineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MachineRepositoryImpl implements MachineRepository {

    private JdbcTemplate jdbcTemplate;

    public MachineRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void insert(List<Machine> machineList) {
        String sql = "insert into machine (id, name) values (?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Machine machine = machineList.get(i);
                ps.setString(1, machine.getId());
                ps.setString(2, machine.getName());
            }

            @Override
            public int getBatchSize() {
                return machineList.size();
            }
        });
    }

    @Override
    public Optional<Machine> findById(String id) {
        String sql = "select id, name from machine where id=?";
        try {
            Machine machine = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                try {
                    return new Machine(rs.getString(1), rs.getString(2));
                } catch (RequiredValueException e) {
                    throw new RuntimeException(e);
                }
            }, id);
            return Optional.of(machine);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Machine> findLatestInsertedParameters() {
        return null;
    }
}
