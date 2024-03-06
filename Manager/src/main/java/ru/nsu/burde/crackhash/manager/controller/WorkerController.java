package ru.nsu.burde.crackhash.manager.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.burde.crackhash.manager.dto.WorkerResponseDTO;

@RestController
public class WorkerController {

    //TODO: здесь должен быть WorkerService
    @PatchMapping("/internal/api/manager/hash/crack/request")
    public void updateTicket(@RequestBody WorkerResponseDTO workerResponseDTO){

    }
}
