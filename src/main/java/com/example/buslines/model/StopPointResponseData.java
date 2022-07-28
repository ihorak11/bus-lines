package com.example.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class StopPointResponseData { //TODO adjust types accordingly
    @JsonProperty("Version")
    public String version;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Result")
    public ArrayList<StopPointDetails> stopPointDetailsList;
}
