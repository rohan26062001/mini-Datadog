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
}
