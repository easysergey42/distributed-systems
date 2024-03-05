package ru.nsu.burde.crackhash.commons;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrackHashManagerRequest {
    private String requestId;
    private int partNumber;
    private int partCount;
    private String hash;
    private int maxLength;
    private Alphabet alphabet;
}
