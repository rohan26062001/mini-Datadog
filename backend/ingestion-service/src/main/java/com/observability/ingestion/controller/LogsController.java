package com.observability.ingestion.controller;

import com.observability.common.dto.LogEvent;
import com.observability.ingestion.service.KafkaLogProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingest")
public class LogsController {

    private final KafkaLogProducer kafkaLogProducer;

    public LogsController(KafkaLogProducer kafkaLogProducer) {
        this.kafkaLogProducer = kafkaLogProducer;
    }

    @PostMapping("/v1/logs")
    public ResponseEntity<String> publishLogsV1(@RequestBody LogEvent logEvent) {
        try {
            kafkaLogProducer.publishLogs(logEvent);
            return new ResponseEntity<>("Log published to Kafka", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
