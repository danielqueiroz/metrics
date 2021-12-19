package com.metrics.stats.domain.repository;

import com.metrics.stats.domain.Parameter;

import java.time.LocalDateTime;
import java.util.List;

public interface ParameterStatsRepository {

    List<Parameter> findParameterStats(LocalDateTime from);
}
