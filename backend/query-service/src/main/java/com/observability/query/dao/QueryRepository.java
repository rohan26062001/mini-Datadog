package com.observability.query.dao;

import com.observability.common.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findByServiceName(String serviceName);
    List<LogEntity> findByLevel(String level);
    List<LogEntity> findByTimestampBetween(long startTimestamp, long endTimestamp);
    List<LogEntity> findByServiceNameAndLevel(String serviceName, String level);
}