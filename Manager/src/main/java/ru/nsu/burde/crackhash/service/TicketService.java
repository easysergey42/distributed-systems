package ru.nsu.burde.crackhash.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.nsu.burde.crackhash.common.WorkerResponseDTO;
import ru.nsu.burde.crackhash.dto.RequestIdDTO;
import ru.nsu.burde.crackhash.dto.ClientRequestDTO;
import ru.nsu.burde.crackhash.dto.ResultDTO;
import ru.nsu.burde.crackhash.domain.Ticket;
import ru.nsu.burde.crackhash.storage.TicketStorage;
import ru.nsu.burde.crackhash.util.Globals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
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

    public void updateTicket(WorkerResponseDTO workerResponseDTO){
        ticketStorage.updateTicket(workerResponseDTO.getRequestId(), workerResponseDTO.getData());
    }

    public ResultDTO getResult(String requestId){
        Ticket ticket = ticketStorage.getById(requestId);
        if (ticket == null){
            return ResultDTO.builder()
                    .status(Ticket.Status.ERROR)
                    .data(null)
                    .build();
        }
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
