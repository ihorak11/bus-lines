package com.example.buslines.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JourneyLineStopPoint { //TODO move to a more suiting directory
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
