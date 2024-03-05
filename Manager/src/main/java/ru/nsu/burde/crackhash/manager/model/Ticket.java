package ru.nsu.burde.crackhash.manager.model;

public class Ticket {
    private Status status;




    public static enum Status{
        READY,
        IN_PROGRESS,
        ERROR
    }
}
