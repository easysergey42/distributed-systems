package ru.nsu.burde.crackhash.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.burde.crackhash.manager.dto.ClientRequestDTO;
import ru.nsu.burde.crackhash.manager.dto.RequestIdDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;
import ru.nsu.burde.crackhash.manager.storage.TicketStorage;
import ru.nsu.burde.crackhash.manager.util.Globals;

import java.time.LocalDateTime;
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




}
