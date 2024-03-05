package ru.nsu.burde.crackhash.manager.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.burde.crackhash.manager.dto.RequestDTO;
import ru.nsu.burde.crackhash.manager.dto.RequestIdDTO;
import ru.nsu.burde.crackhash.manager.dto.ResultDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {
    @PostMapping("/api/hash/crack")
    public ResponseEntity<RequestIdDTO> crackHash(@RequestBody RequestDTO requestDTO){
        return ResponseEntity.ok().body(RequestIdDTO.builder().requestId(UUID.randomUUID().toString()).build());
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
