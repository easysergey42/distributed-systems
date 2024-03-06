package ru.nsu.burde.crackhash.worker.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.burde.crackhash.worker.dto.TaskForWorkerDTO;
import ru.nsu.burde.crackhash.worker.service.CrackHashService;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ManagerController {
    private final CrackHashService crackHashService;
    @PostMapping("/internal/api/worker/hash/crack/task")
    public ResponseEntity<String> handleTask(@RequestBody TaskForWorkerDTO task){
        log.info("received task #" + task.getRequestId());

//        crackHashService

        return ResponseEntity.ok().body(task.getRequestId());
    }
}
