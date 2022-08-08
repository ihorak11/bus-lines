package com.example.buslines.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JourneyPatternStopResponse {
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("ExecutionTime")
    private int executionTime;
    @JsonProperty("ResponseData")
    private JourneyResponseData journeyResponseData;
}
