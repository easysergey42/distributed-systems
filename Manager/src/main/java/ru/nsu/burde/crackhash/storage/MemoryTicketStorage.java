package ru.nsu.burde.crackhash.storage;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.nsu.burde.crackhash.common.WorkerResponseDTO;
import ru.nsu.burde.crackhash.domain.Ticket;
import ru.nsu.burde.crackhash.storage.updater.PeriodicTicketUpdater;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Log4j2
public class MemoryTicketStorage implements TicketStorage{

    //TODO: можно ли как то реализовать чтение из пропертей?
//    @Value("${manager.ticketExpireTimeInMinutes:2}")
//    private int ticketExpireTimeInMinutes;
    private final ConcurrentHashMap<String, Ticket> ticketStorage;
    private final PeriodicTicketUpdater updater;


    public MemoryTicketStorage() {
        this.ticketStorage = new ConcurrentHashMap<>();
        updater = new PeriodicTicketUpdater(ticketStorage);
        updater.startPeriodicUpdate();
    }

    @Override
    public void store(Ticket ticket) {
        ticketStorage.put(ticket.getRequestId(), ticket);
    }

    @Override
    public Ticket getById(String requestId) {
        return ticketStorage.get(requestId);
//        ticketStorage.entrySet()
    }

    @Override
    public void updateTicket(String id, List<String> data) {
        ticketStorage.computeIfPresent(id, (k,v) -> {
            v.setCompletedTasks(v.getCompletedTasks()+1);
            log.info(String.format("ticket #%s tasks completed: %d out of %d", v.getRequestId(), v.getCompletedTasks(), v.getTotalTasks()));
            if (v.getCompletedTasks() == v.getTotalTasks()){
                v.setStatus(Ticket.Status.READY);
            }
            if (v.getResult() == null){
                v.setResult(new ArrayList<>(data));
            }
            else{
                var newData = v.getResult();
                newData.addAll(data);
            }
            log.info(String.format("Ticket #%s has been updated", k));
            return v;
        });
    }

    @Override
    public void deleteTicket(String id) {
        ticketStorage.remove(id);
    }

}
