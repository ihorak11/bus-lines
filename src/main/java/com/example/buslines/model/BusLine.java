package com.example.buslines.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class BusLine {
    private String designation;
    private String direction;
    private Set<String> stopNames;
    private int noOfStops;
}