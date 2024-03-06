package ru.nsu.burde.crackhash.manager.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class Ticket {
    private Status status;
    private String requestId;
    private String hash;
    private int maxLength;
    private LocalDateTime createdAt;
    private List<String> result;
    private int totalTasks;
    private int completedTasks;

    public enum Status{
        READY,
        IN_PROGRESS,
        ERROR
    }
}
