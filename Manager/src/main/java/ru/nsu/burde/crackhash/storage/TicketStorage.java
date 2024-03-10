package ru.nsu.burde.crackhash.storage;

import ru.nsu.burde.crackhash.common.WorkerResponseDTO;
import ru.nsu.burde.crackhash.domain.Ticket;

import java.util.List;

public interface TicketStorage {

    void store(Ticket ticket);

    Ticket getById(String requestId);

    void updateTicket(String id, List<String> data);

    void deleteTicket(String id);

}
