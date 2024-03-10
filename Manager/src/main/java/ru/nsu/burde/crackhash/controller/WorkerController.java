package ru.nsu.burde.crackhash.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.burde.crackhash.common.WorkerResponseDTO;
import ru.nsu.burde.crackhash.service.TicketService;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WorkerController {
    private final TicketService ticketService;
    //TODO: здесь должен быть WorkerService
    @PatchMapping("/internal/api/manager/hash/crack/request")
    public ResponseEntity<String> updateTicket(@RequestBody WorkerResponseDTO workerResponseDTO){
//        log.info("Received response from worker: " + workerResponseDTO);
        ticketService.updateTicket(workerResponseDTO)   ;
        return ResponseEntity.ok().body(workerResponseDTO.getRequestId());
    }
}
