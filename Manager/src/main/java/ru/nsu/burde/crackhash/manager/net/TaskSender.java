package ru.nsu.burde.crackhash.manager.net;

import ru.nsu.burde.crackhash.manager.dto.TaskForWorkerDTO;

public interface TaskSender {
    void sendTaskToWorker(TaskForWorkerDTO task);
}
