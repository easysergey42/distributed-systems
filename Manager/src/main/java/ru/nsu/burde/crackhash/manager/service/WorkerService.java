package ru.nsu.burde.crackhash.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.burde.crackhash.manager.dto.TaskForWorkerDTO;
import ru.nsu.burde.crackhash.manager.dto.WorkerResponseDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;
import ru.nsu.burde.crackhash.manager.net.TaskSender;
import ru.nsu.burde.crackhash.manager.storage.TicketStorage;
import ru.nsu.burde.crackhash.manager.util.Globals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WorkerService {
    private TicketStorage ticketStorage;
    private ExecutorService threadPool;
    private TaskSender taskSender;

    //TODO: Что будет если убрать Autowired?
    @Autowired
    public WorkerService(TicketStorage ticketStorage, TaskSender taskSender) {
        this.ticketStorage = ticketStorage;
        this.taskSender = taskSender;
        this.threadPool = Executors.newFixedThreadPool(Globals.SENDERS_THREAD_POOL_SIZE);
    }


    public void sendTasksToWorkers(String ticketId){
        Ticket ticket = ticketStorage.getById(ticketId);
        for (int i = 0; i < Globals.WORKER_NODES_NUM; i++) {
            var task = convertToDTO(ticket, i);
            taskSender.sendTaskToWorker(task);
        }
    }



    //TODO: вызвать обновление Тикета здесь.
    public void handleWorkerResponse(WorkerResponseDTO responseDTO){

    }

    private TaskForWorkerDTO convertToDTO(Ticket ticket, int partNumber){
        return TaskForWorkerDTO
                .builder()
                .hash(ticket.getHash())
                .requestId(ticket.getRequestId())
                .maxLength(ticket.getMaxLength())
                .partNumber(partNumber)
                .partCount(ticket.getTotalTasks())
                .alphabet(Globals.DEFAULT_ALPHABET.split(""))
                .build();
    }
}
