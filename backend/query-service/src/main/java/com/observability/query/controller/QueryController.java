package com.observability.query.controller;

import com.observability.common.entity.LogEntity;
import com.observability.query.service.QueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/query")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/v1/logs")
    public ResponseEntity<List<LogEntity>> getAllLogs() {
        return new ResponseEntity<>(queryService.getAllLogs(), HttpStatus.OK);
    }

    @GetMapping("/v1/logs/service")
    public ResponseEntity<List<LogEntity>> getLogsByService(@RequestParam String serviceName) {
        return new ResponseEntity<>(queryService.getLogsByServiceName(serviceName), HttpStatus.OK);
    }

    @GetMapping("/v1/logs/level")
    public ResponseEntity<List<LogEntity>> getLogsByLevel(@RequestParam String level) {
        return new ResponseEntity<>(queryService.getLogsByLevel(level), HttpStatus.OK);
    }

    @GetMapping("/v1/logs/time")
    public ResponseEntity<List<LogEntity>> getLogsByTimeRange(@RequestParam long start, @RequestParam long end) {
        return new ResponseEntity<>(queryService.getLogsByTimeRange(start, end), HttpStatus.OK);
    }

    @GetMapping("/v1/logs/service-level")
    public ResponseEntity<List<LogEntity>> getLogsByServiceAndLevel(@RequestParam String serviceName, @RequestParam String level) {
        return new ResponseEntity<>(queryService.getLogsByServiceAndLevel(serviceName, level), HttpStatus.OK);
    }
}