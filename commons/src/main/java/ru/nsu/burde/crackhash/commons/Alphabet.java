package ru.nsu.burde.crackhash.commons;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Alphabet {
    private String[] symbols;
}
