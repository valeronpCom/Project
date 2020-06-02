package com.restApp.Repository;

import com.restApp.Models.Statistics;
import org.springframework.data.repository.CrudRepository;

public interface StatisticsRepository extends CrudRepository<Statistics, Long> {
    Statistics findByProcessId(Integer id);
}