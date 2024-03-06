package ru.nsu.burde.crackhash.manager.storage;

import ru.nsu.burde.crackhash.manager.dto.WorkerResponseDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;

public interface TicketStorage {
    public void store(Ticket ticket);

    public Ticket getById(String requestId);

    public void updateTicket(String id, WorkerResponseDTO workerResponseDTO);

    public void deleteTicket(String id);

}
