package ru.nsu.burde.crackhash.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.burde.crackhash.manager.dto.ClientRequestDTO;
import ru.nsu.burde.crackhash.manager.dto.RequestIdDTO;
import ru.nsu.burde.crackhash.manager.dto.ResultDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;
import ru.nsu.burde.crackhash.manager.service.TicketService;
import ru.nsu.burde.crackhash.manager.service.WorkerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final TicketService ticketService;
    private final WorkerService workerService;

    @PostMapping("/api/hash/crack")
    public ResponseEntity<RequestIdDTO> crackHash(@RequestBody ClientRequestDTO clientRequestDTO){
        var requestIdDTO = ticketService.registerNewTicket(clientRequestDTO);
        log.info("new ticket was registered!");
        workerService.handleTicket(requestIdDTO.getRequestId());
        return ResponseEntity.ok().body(requestIdDTO);
    }

    @GetMapping("/api/hash/status")
    public ResponseEntity<ResultDTO> getResult(@RequestParam(value = "requestId") String requestId){

        return ResponseEntity.ok().body(
                ResultDTO
                        .builder()
                        .status(Ticket.Status.READY)
                        .data(List.of("fine"))
                        .build()
        );
    }
}
