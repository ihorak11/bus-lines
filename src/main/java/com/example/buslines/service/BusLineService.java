package com.example.buslines.service;

import com.example.buslines.api.client.SLApiClient;
import com.example.buslines.api.response.JourneyLineStopPoint;
import com.example.buslines.api.response.JourneyPatternStopResponse;
import com.example.buslines.api.response.StopPointDetails;
import com.example.buslines.model.TopBusLinesScoreboard;
import com.example.buslines.util.BusLineUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class BusLineService {
    private SLApiClient slApiClient;
    private BusLineUtils busLineUtils;

    public TopBusLinesScoreboard getTop10BusLines() {
        log.info("Fetching the bus line scoreboard");

        JourneyPatternStopResponse journeyResponse = slApiClient.getSlBusLines();

        ArrayList<JourneyLineStopPoint> stopPointList = journeyResponse.getJourneyResponseData().getJourneyLineStopPointList();
        Map<String, Set<String>> busLineToStopIdMap = busLineUtils.extractBusLineStopIdMap(stopPointList);

        //the API seems to not return the details for all the stops, resulting in inability to extract all of the stop names from the response
        ArrayList<StopPointDetails> stopPointDetailsList = slApiClient.getStopPointDetails().getResponseData().getStopPointDetailsList();

        return busLineUtils.createTopBusLinesScoreboardObject(busLineToStopIdMap, stopPointDetailsList);
    }
}