package ru.nsu.burde.crackhash.net;


import ru.nsu.burde.crackhash.common.TaskForWorkerDTO;

public interface TaskSender {
    void sendTaskToWorker(TaskForWorkerDTO task);
}
