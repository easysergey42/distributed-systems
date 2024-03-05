package ru.nsu.burde.crackhash.manager.dto;

import lombok.Data;

@Data
public class ClientRequestDTO {
    String hash;
    int maxLength;
}
