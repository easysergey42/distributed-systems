package ru.nsu.burde.crackhash.manager.dto;


import lombok.Builder;
import lombok.Data;
import ru.nsu.burde.crackhash.manager.model.Ticket;

import java.util.List;

@Data
@Builder
public class ResultDTO {
    private Ticket.Status status;
    private List<String> data;
}
