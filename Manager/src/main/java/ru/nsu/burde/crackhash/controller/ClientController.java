package ru.nsu.burde.crackhash.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.burde.crackhash.dto.ClientRequestDTO;
import ru.nsu.burde.crackhash.dto.RequestIdDTO;
import ru.nsu.burde.crackhash.dto.ResultDTO;
import ru.nsu.burde.crackhash.service.TicketService;
import ru.nsu.burde.crackhash.service.WorkerService;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final TicketService ticketService;
    private final WorkerService workerService;

    @PostMapping("/api/hash/crack")
    public ResponseEntity<RequestIdDTO> crackHash(@RequestBody ClientRequestDTO clientRequestDTO){
        var requestIdDTO = ticketService.registerNewTicket(clientRequestDTO);
        log.info("new ticket was registered with requestId = " + requestIdDTO.getRequestId());
        workerService.sendTasksToWorkers(requestIdDTO.getRequestId());
        return ResponseEntity.ok().body(requestIdDTO);
    }

    @GetMapping("/api/hash/status")
    public ResponseEntity<ResultDTO> getResult(@RequestParam(value = "requestId") String requestId){

        return ResponseEntity.ok().body(ticketService.getResult(requestId));
    }
}
