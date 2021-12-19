package com.metrics.stats.domain.repository;

import com.metrics.stats.domain.ParameterStats;

import java.time.LocalDateTime;
import java.util.List;

public interface ParameterStatsRepository {

    List<ParameterStats> findParameterStats(String machineId, String parameterName, LocalDateTime from);
}
