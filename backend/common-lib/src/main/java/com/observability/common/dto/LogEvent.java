package com.observability.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {
    private String serviceName;
    private String level;
    private String message;
    private long timestamp;
}
