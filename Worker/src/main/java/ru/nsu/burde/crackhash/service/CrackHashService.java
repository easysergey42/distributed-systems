package ru.nsu.burde.crackhash.service;

import lombok.extern.log4j.Log4j2;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nsu.burde.crackhash.common.TaskForWorkerDTO;
import ru.nsu.burde.crackhash.common.WorkerResponseDTO;
import ru.nsu.burde.crackhash.utils.HashCalculations;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Service
@Log4j2
public class CrackHashService {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    @Value("${manager.url}")
    private String managerUrl;
    public CrackHashService() {
        this.restTemplate = new RestTemplate();
        var requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void taskRoutine(TaskForWorkerDTO task){
        executorService.submit(() -> {
            List<String> data = findMatchingWords(task);
//            log.info("Task #" + task.getRequestId() + " executed, result is " + data.toString());

            WorkerResponseDTO response = WorkerResponseDTO.builder()
                    .requestId(task.getRequestId())
                    .data(data)
                    .partNumber(task.getPartNumber())
                    .build();
            log.info("Task #" + task.getRequestId() + " executed, result is " + response.toString());

            URI uri = URI.create(managerUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<WorkerResponseDTO> httpRequest = new HttpEntity<>(response, headers);
            var x = restTemplate.patchForObject(uri, httpRequest, String.class);
            log.info(String.format("patch request #%s was sent to %s", response.getRequestId(), uri));
        });
        log.info(String.format("Task #%s execution has started", task.getRequestId()));
    }


    public List<String> findMatchingWords(TaskForWorkerDTO task){
        var wordsStream = streamOfWords(task.getAlphabet(), task.getMaxLength(), getEqualPart(task));

        return wordsStream
                .filter(word -> HashCalculations.hashMatches(word, task.getHash()))
                .toList();
    }

    private static Stream<String> streamOfWords(String[] alphabet, int maxLength, Part part) {
        var stream = Generator
                .permutation(alphabet )
                .withRepetitions(1)
                .stream();
        for (int i = 2; i <= maxLength; i++) {
            stream = Stream
                    .concat(stream,
                            Generator
                                    .permutation(alphabet)
                                    .withRepetitions(i)
                                    .stream());
        }
        return stream
                .skip(part.displacement)
                .limit(part.amount)
                .map(list -> String.join("", list));
    }


    private static class Part{
        private long displacement = 0;
        private long amount = 0;

    }
    private static Part getEqualPart(TaskForWorkerDTO task){
        int numOfWorkers = task.getPartCount();
        int alphabetSize = task.getAlphabet().length;
        long wordCount = alphabetSize;
        for (int i = 2; i <= task.getMaxLength(); ++i){
            wordCount += (long) Math.pow(alphabetSize, i);
        }

        long wordsPerWorker = wordCount / numOfWorkers;
        long remainingWords = wordCount % numOfWorkers;

        int partNum = task.getPartNumber();

        var part = new Part();
        part.displacement = wordsPerWorker * partNum + Long.min(remainingWords, partNum);
        part.amount = wordsPerWorker + (partNum < remainingWords ? 1 : 0);

        return part;
    }

}
