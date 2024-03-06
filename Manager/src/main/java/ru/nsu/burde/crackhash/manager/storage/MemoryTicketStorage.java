package ru.nsu.burde.crackhash.manager.storage;


import org.springframework.stereotype.Repository;
import ru.nsu.burde.crackhash.manager.dto.WorkerResponseDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryTicketStorage implements TicketStorage{

    private final ConcurrentHashMap<String, Ticket> ticketStorage;

    public MemoryTicketStorage() {
        this.ticketStorage = new ConcurrentHashMap<>();
    }

    @Override
    public void store(Ticket ticket) {
        ticketStorage.put(ticket.getRequestId(), ticket);
    }

    @Override
    public Ticket getById(String requestId) {
        return ticketStorage.get(requestId);
    }

    @Override
    public void updateTicket(String id, WorkerResponseDTO workerResponseDTO) {

    }

    @Override
    public void deleteTicket(String id) {
        ticketStorage.remove(id);
    }

}
