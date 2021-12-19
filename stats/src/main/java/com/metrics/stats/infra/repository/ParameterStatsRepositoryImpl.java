package com.metrics.stats.infra.repository;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.repository.ParameterStatsRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ParameterStatsRepositoryImpl implements ParameterStatsRepository {
    @Override
    public List<Parameter> findParameterStats(LocalDateTime from) {
        /*
        SELECT *
                FROM (
                        SELECT P.*, ROWNUM() AS LINE_NUMBER, PARAM.TOTAL, DECODE(PARAM.TOTAL%2,0,PARAM.TOTAL/2, (PARAM.TOTAL/2) +1) INDICE
                        FROM PARAMETER P, (
                                SELECT
                        MACHINE_ID, NAME,  COUNT(*) AS TOTAL
        FROM PARAMETER
        WHERE
                MACHINE_ID = 'tokyo' AND NAME ='memory'
        GROUP BY MACHINE_ID, NAME) PARAM
        WHERE P.MACHINE_ID = PARAM.MACHINE_ID AND P.NAME=PARAM.NAME
        ORDER BY REPORTED_TIME
        ) WHERE LINE_NUMBER = INDICE */

        return null;
    }
}
