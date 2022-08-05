package com.example.buslines.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StopPointResponse {
    @JsonProperty("StatusCode")
    public int statusCode;
    @JsonProperty("Message")
    public Object message;
    @JsonProperty("ExecutionTime")
    public int executionTime;
    @JsonProperty("ResponseData")
    public StopPointResponseData responseData;
}