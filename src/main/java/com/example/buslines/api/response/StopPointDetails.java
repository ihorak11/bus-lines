package com.example.buslines.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StopPointDetails {
    @JsonProperty("StopPointNumber")
    private String stopPointNumber;
    @JsonProperty("StopPointName")
    private String stopPointName;
    @JsonProperty("StopAreaNumber")
    private String stopAreaNumber;
    @JsonProperty("LocationNorthingCoordinate")
    private String locationNorthingCoordinate;
    @JsonProperty("LocationEastingCoordinate")
    private String locationEastingCoordinate;
    @JsonProperty("ZoneShortName")
    private String zoneShortName;
    @JsonProperty("StopAreaTypeCode")
    private String stopAreaTypeCode;
    @JsonProperty("LastModifiedUtcDateTime")
    private String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    private String existsFromDate;
}