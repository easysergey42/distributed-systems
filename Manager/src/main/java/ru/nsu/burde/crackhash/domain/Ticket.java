package ru.nsu.burde.crackhash.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    @Override
    public String toString() {
        return String.format("""
                Ticket:
                requestId: %s
                status: %s
                hash: %s
                maxLength: %d
                createdAt: %s
                result: %s
                totalTasks: %d
                completedTasks: %d""", requestId,
                status,
                hash,
                maxLength,
                createdAt,
                result,
                totalTasks,
                completedTasks);
    }
}
