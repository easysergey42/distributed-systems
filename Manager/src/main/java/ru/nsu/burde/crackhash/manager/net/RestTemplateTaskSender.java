package ru.nsu.burde.crackhash.manager.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nsu.burde.crackhash.manager.dto.TaskForWorkerDTO;

import java.net.URI;

@Component
@Log4j2
public class RestTemplateTaskSender implements TaskSender {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RestTemplateTaskSender() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void sendTaskToWorker(TaskForWorkerDTO task) {
        int nodeNumber = task.getPartNumber()+1;
        String workerHost = "http://worker"+nodeNumber+":8080"+"/internal/api/worker/hash/crack/task";
        log.info("Task was sent to worker host: "+workerHost);

        URI uri = URI.create(workerHost);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TaskForWorkerDTO> httpRequest = new HttpEntity<>(task, headers);

        var responseEntity = restTemplate.postForEntity(uri, httpRequest, String.class);
        log.info("sending request");
        log.info("Response status code = " + responseEntity.getStatusCode());
    }
}
