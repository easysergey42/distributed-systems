package ru.nsu.burde.crackhash.manager.storage;

import ru.nsu.burde.crackhash.manager.dto.WorkerResponseDTO;
import ru.nsu.burde.crackhash.manager.model.Ticket;

public interface TicketStorage {
    void store(Ticket ticket);

    Ticket getById(String requestId);

    void updateTicket(String id, WorkerResponseDTO workerResponseDTO);

    void deleteTicket(String id);

}
