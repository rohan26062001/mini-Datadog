package com.observability.query.service;

import com.observability.common.entity.LogEntity;
import com.observability.query.dao.QueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public List<LogEntity> getAllLogs() {
        return queryRepository.findAll();
    }

    public List<LogEntity> getLogsByServiceName(String serviceName) {
        return queryRepository.findByServiceName(serviceName);
    }

    public List<LogEntity> getLogsByLevel(String level) {
        return queryRepository.findByLevel(level);
    }

    public List<LogEntity> getLogsByTimeRange(long startTimestamp, long endTimestamp) {
        return queryRepository.findByTimestampBetween(startTimestamp, endTimestamp);
    }

    public List<LogEntity> getLogsByServiceAndLevel(String serviceName, String level) {
        return queryRepository.findByServiceNameAndLevel(serviceName, level);
    }
}
