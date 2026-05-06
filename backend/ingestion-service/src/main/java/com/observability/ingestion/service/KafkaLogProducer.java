package com.observability.ingestion.service;

import com.observability.common.dto.LogEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaLogProducer {

    private final KafkaTemplate<String, LogEvent> kafkaTemplate;
    private final String topicName;

    public KafkaLogProducer(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = "logs";
    }

    public void publishLogs(LogEvent logEvent) {
        kafkaTemplate.send(topicName, logEvent);
    }
}
