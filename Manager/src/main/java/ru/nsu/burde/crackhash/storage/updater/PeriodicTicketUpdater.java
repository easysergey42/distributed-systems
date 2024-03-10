package ru.nsu.burde.crackhash.storage.updater;

import lombok.extern.log4j.Log4j2;
import ru.nsu.burde.crackhash.domain.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
public class PeriodicTicketUpdater {

    private final ScheduledExecutorService scheduler;
    private final ConcurrentHashMap<String, Ticket> storage;
    private final long ticketExpireTimeInMinutes = 2;

    public PeriodicTicketUpdater(ConcurrentHashMap<String, Ticket> storage) {
        this.storage = storage;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startPeriodicUpdate(){
        scheduler.scheduleAtFixedRate(() -> storage.forEach((key, ticket) -> {
            if (ticket.getStatus() == Ticket.Status.IN_PROGRESS &&
                    Duration.between(ticket.getCreatedAt(), LocalDateTime.now()).getSeconds() > 60*ticketExpireTimeInMinutes){
                ticket.setStatus(Ticket.Status.ERROR);
                log.info(String.format("Time is up for Ticket #%s!", key));
            }
        }),0,1, TimeUnit.MINUTES);
    }
}
