package ru.nsu.burde.crackhash.worker.service;

import lombok.extern.log4j.Log4j2;
import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Service;
import ru.nsu.burde.crackhash.worker.dto.TaskForWorkerDTO;
import ru.nsu.burde.crackhash.worker.dto.WorkerResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Log4j2
public class CrackHashService {


    public void calculateTask(TaskForWorkerDTO task){
        var part = getEqualPart(task);
        List<String> data = new ArrayList<>();

        int maxLen = task.getMaxLength();
        var stream = Generator.permutation(task.getAlphabet()).withRepetitions(1).stream();
        for (int i = 2; i <= maxLen; i++) {
            stream = Stream.concat(stream, Generator.permutation(task.getAlphabet()).withRepetitions(i).stream());
        }

        stream.skip(part.displacement).limit(part.amount).forEach(this::calcHash);
    }

    private void calcHash(List<String> wordList){

    }

    private static class Part{
        private long displacement = 0;
        private long amount = 0;

    }
    private Part getEqualPart(TaskForWorkerDTO task){
        int numOfWorkers = task.getPartCount();
//        Part[] parts = new Part[numOfWorkers];
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
