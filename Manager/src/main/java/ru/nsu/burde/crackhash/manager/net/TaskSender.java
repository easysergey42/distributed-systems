package ru.nsu.burde.crackhash.manager.net;

import ru.nsu.burde.crackhash.manager.dto.TaskForWorkerDTO;

public interface TaskSender {
    public void sendTaskToWorker(TaskForWorkerDTO task);
}
