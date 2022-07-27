package com.example.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Result { //TODO adjust types accordingly
    @JsonProperty("LineNumber")
    public String lineNumber;
    @JsonProperty("DirectionCode")
    public String directionCode;
    @JsonProperty("JourneyPatternPointNumber")
    public String journeyPatternPointNumber;
    @JsonProperty("LastModifiedUtcDateTime")
    public String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    public String existsFromDate;
}
