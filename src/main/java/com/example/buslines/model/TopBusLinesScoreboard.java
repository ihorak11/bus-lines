package com.example.buslines.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Builder
public class TopBusLinesScoreboard {
    private ArrayList<BusLine> busLines;
}