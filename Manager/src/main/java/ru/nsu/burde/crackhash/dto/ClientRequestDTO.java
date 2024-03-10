package ru.nsu.burde.crackhash.dto;

import lombok.Data;

@Data
public class ClientRequestDTO {
    String hash;
    int maxLength;
}
