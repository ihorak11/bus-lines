package com.example.buslines.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Builder
public class BusLine {
    private String designation;
    private String direction;
    private ArrayList<String> stopNames;
}