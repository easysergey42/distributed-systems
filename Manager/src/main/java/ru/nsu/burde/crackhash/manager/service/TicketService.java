package ru.nsu.burde.crackhash.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.burde.crackhash.manager.dto.ClientRequestDTO;
import ru.nsu.burde.crackhash.manager.dto.RequestIdDTO;
import ru.nsu.burde.crackhash.manager.dto.ResultDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;
import ru.nsu.burde.crackhash.manager.storage.TicketStorage;
import ru.nsu.burde.crackhash.manager.util.Globals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketStorage ticketStorage;

    public RequestIdDTO registerNewTicket(ClientRequestDTO clientRequestDTO){
        String requestId = UUID.randomUUID().toString();
        Ticket ticket = Ticket
                .builder()
                .requestId(requestId)
                .status(Ticket.Status.IN_PROGRESS)
                .hash(clientRequestDTO.getHash())
                .maxLength(clientRequestDTO.getMaxLength())
                .createdAt(LocalDateTime.now())
                .totalTasks(Globals.WORKER_NODES_NUM)
                .build();

        ticketStorage.store(ticket);
        return RequestIdDTO.builder().requestId(requestId).build();
    }

    public ResultDTO getResult(String requestId){
        Ticket ticket = ticketStorage.getById(requestId);
        var status = ticket.getStatus();
        List<String> data = null;
        if (status == Ticket.Status.READY){
            ticketStorage.deleteTicket(requestId);
            data = ticket.getResult();
        }
        return ResultDTO.builder()
                .status(status)
                .data(data)
                .build();
    }


}
