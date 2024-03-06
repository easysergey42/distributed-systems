package ru.nsu.burde.crackhash.manager.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskForWorkerDTO {
    private String requestId;
    private int partNumber;
    private int partCount;
    private String hash;
    private int maxLength;
    private String[] alphabet;
}
