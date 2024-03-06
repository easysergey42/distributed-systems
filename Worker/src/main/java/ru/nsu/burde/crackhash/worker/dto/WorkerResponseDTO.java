package ru.nsu.burde.crackhash.worker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkerResponseDTO {

    private String requestId;
    private int partNumber;
    private List<String> data;
}
