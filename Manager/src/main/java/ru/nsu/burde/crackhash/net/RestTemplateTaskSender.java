package ru.nsu.burde.crackhash.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nsu.burde.crackhash.common.TaskForWorkerDTO;

import java.net.URI;
import java.net.URL;

@Component
@Log4j2
public class RestTemplateTaskSender implements TaskSender {

    private final RestTemplate restTemplate;

    @Value("${worker.hostName:http://worker}")
    private String workerAddr;
    @Value("${worker.port:8080}")
    private String workerPort;
    @Value("${worker.endpoint:/internal/api/worker/hash/crack/task}")
    private String workerEndpoint;

    public RestTemplateTaskSender() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void sendTaskToWorker(TaskForWorkerDTO task) {
        int nodeNumber = task.getPartNumber()+1;
        //TODO: set worker nodeNumber here
        String workerUrl = workerAddr+":"+workerPort+workerEndpoint;

        URI uri = URI.create(workerUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TaskForWorkerDTO> httpRequest = new HttpEntity<>(task, headers);

        var responseEntity = restTemplate.postForEntity(uri, httpRequest, String.class);
        log.info(String.format("Task #%s part %d was sent to url: %s",task.getRequestId(), task.getPartNumber(),workerUrl));
    }
}
