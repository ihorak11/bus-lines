package com.example.buslines.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LineDirectionPair {
    private String lineNumber;
    private String direction;

}
