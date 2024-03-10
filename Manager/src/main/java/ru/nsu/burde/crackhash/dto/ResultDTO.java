package ru.nsu.burde.crackhash.dto;


import lombok.Builder;
import lombok.Data;
import ru.nsu.burde.crackhash.domain.Ticket;

import java.util.List;

@Data
@Builder
public class ResultDTO {
    private Ticket.Status status;
    private List<String> data;

}
