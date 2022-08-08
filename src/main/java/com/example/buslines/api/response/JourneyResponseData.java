package com.example.buslines.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class JourneyResponseData {
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Result")
    private ArrayList<JourneyLineStopPoint> journeyLineStopPointList;
}
