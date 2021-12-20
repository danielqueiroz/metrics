package com.metrics.stats.infra.repository;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.ParameterStats;
import com.metrics.stats.domain.repository.ParameterStatsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ParameterStatsRepositoryImpl implements ParameterStatsRepository {

    private JdbcTemplate jdbcTemplate;

    public ParameterStatsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ParameterStats> findParameterStats(String machineId, String parameterName, LocalDateTime from) {

        String sql = "" +
                "SELECT MACHINE_ID, NAME, TRUNC(AVG(VALUE),2) AS AVERAGE, MIN(VALUE) AS MINIMUM, MAX(VALUE) AS MAXIMUM, COUNT(1) AS TOTAL " +
                "FROM PARAMETER " +
                "WHERE " +
                "  (? IS NULL OR MACHINE_ID = ?) AND " +
                "  (? IS NULL OR NAME = ?) AND " +
                "  REPORTED_TIME >= ? " +
                "GROUP BY MACHINE_ID, NAME";

        List<ParameterStats.Builder> parameterStatsList = jdbcTemplate.query(sql, (rs, rowNum) -> {
            ParameterStats.Builder builder = new ParameterStats.Builder();
            builder.setMachineId(rs.getString(1));
            builder.setParameterName(rs.getString(2));
            builder.setAverage(rs.getDouble(3));
            builder.setMin(rs.getLong(4));
            builder.setMax(rs.getLong(5));
            builder.setCount(rs.getLong(6));
            return builder;
        }, machineId, machineId, parameterName, parameterName, from);

        List<ParameterStats> params = new ArrayList<>();
        for (ParameterStats.Builder paramBuilder: parameterStatsList) {
            List<Integer> indexes = new ArrayList<>();
            int middle = (int) (paramBuilder.getCount() / 2);

            if(paramBuilder.getCount() % 2 == 0) {
                indexes.add(middle);
            }
            indexes.add(middle +1);

            String inSql = String.join(",", Collections.nCopies(indexes.size(), "?"));

            String sqlMedian = "" +
                    "SELECT TRUNC(AVG(VALUE),2) AS MEDIAN " +
                    "FROM ( " +
                    "SELECT VALUE, ROWNUM() AS LINE_NUMBER " +
                    "FROM PARAMETER " +
                    "WHERE MACHINE_ID = ? AND NAME = ? " +
                    "AND REPORTED_TIME >= ? " +
                    "ORDER BY REPORTED_TIME " +
                    String.format(") WHERE LINE_NUMBER IN (%s)",inSql);

            if(paramBuilder.getCount() % 2 == 0) {
                paramBuilder.setMedian(jdbcTemplate.queryForObject(sqlMedian, Double.class, paramBuilder.getMachineId(),
                        paramBuilder.getParameterName(), from, indexes.get(0), indexes.get(1)));
            } else {
                paramBuilder.setMedian(jdbcTemplate.queryForObject(sqlMedian, Double.class, paramBuilder.getMachineId(),
                        paramBuilder.getParameterName(), from, indexes.get(0)));
            }


            params.add(paramBuilder.build());
        }
        return params;
    }
}
