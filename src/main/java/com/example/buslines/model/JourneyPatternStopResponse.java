package com.example.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JourneyPatternStopResponse { //TODO adjust types accordingly
    @JsonProperty("StatusCode")
    public int statusCode;
    @JsonProperty("Message")
    public Object message;
    @JsonProperty("ExecutionTime")
    public int executionTime;
    @JsonProperty("ResponseData")
    public JourneyResponseData journeyResponseData; //TODO custom de-serializer would prevent code duplication
}
