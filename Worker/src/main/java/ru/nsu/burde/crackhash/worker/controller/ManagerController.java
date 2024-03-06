package ru.nsu.burde.crackhash.worker.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.burde.crackhash.worker.dto.TaskForWorkerDTO;

@RestController
@Log4j2
public class ManagerController {

    @PostMapping("/internal/api/worker/hash/crack/task")
    public ResponseEntity<String> handleTask(@RequestBody TaskForWorkerDTO task){
        log.info("I received task! I have part #" + (task.getPartNumber()+1));
        return ResponseEntity.ok().body("Nice");
    }
}
