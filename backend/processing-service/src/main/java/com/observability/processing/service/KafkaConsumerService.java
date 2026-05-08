package com.observability.processing.service;

import com.observability.common.dto.LogEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final LogPersistenceService logPersistenceService;

    public KafkaConsumerService(LogPersistenceService logPersistenceService) {
        this.logPersistenceService = logPersistenceService;
    }

    @KafkaListener(topics = "${kafka.topic.logs}", groupId = "${spring.kafka.consumer.group.id}")
    public void listen(LogEvent logEvent) {
        logPersistenceService.persist(logEvent);
    }
}
