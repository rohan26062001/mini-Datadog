package com.observability.processing.service;

import com.observability.common.dto.LogEvent;
import com.observability.processing.dao.LogEventRepository;
import com.observability.common.entity.LogEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
public class LogPersistenceService {

    private final LogEventRepository logEventRepository;

    public LogPersistenceService(LogEventRepository logEventRepository) {
        this.logEventRepository = logEventRepository;
    }

    public void persist(LogEvent logEvent) {
        LogEntity logEntity = mapToEntity(logEvent);
        logEventRepository.save(logEntity);
    }

    private static @NonNull LogEntity mapToEntity(LogEvent logEvent) {
        LogEntity logEntity = new LogEntity();
        logEntity.setServiceName(logEvent.getServiceName());
        logEntity.setLevel(logEvent.getLevel().name());
        logEntity.setMessage(logEvent.getMessage());
        logEntity.setTimestamp(logEvent.getTimestamp());
        return logEntity;
    }
}
