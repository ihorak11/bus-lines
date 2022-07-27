package com.example.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JourneyResponse { //TODO adjust types accordingly
    @JsonProperty("StatusCode")
    public int statusCode;
    @JsonProperty("Message")
    public Object message;
    @JsonProperty("ExecutionTime")
    public int executionTime;
    @JsonProperty("ResponseData")
    public ResponseData responseData;
}
