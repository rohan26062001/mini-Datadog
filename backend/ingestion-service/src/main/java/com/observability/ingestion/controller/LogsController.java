package com.observability.ingestion.controller;

import com.observability.common.dto.LogEvent;
import com.observability.ingestion.service.KafkaLogProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogsController {

    private final KafkaLogProducer kafkaLogProducer;

    public LogsController(KafkaLogProducer kafkaLogProducer) {
        this.kafkaLogProducer = kafkaLogProducer;
    }

    @PostMapping("/logs")
    public void publishLogs(@RequestBody LogEvent logEvent) {
        kafkaLogProducer.publishLogs(logEvent);
    }
}
