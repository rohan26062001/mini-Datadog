package com.observability.ingestion.service;

import com.observability.common.dto.LogEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaLogProducer {

    private final KafkaTemplate<String, LogEvent> kafkaTemplate;
    @Value("${kafka.topic.logs}")
    private String topicName;

    public KafkaLogProducer(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishLogs(LogEvent logEvent) {
        kafkaTemplate.send(topicName, logEvent.getServiceName(), logEvent);
    }
}
