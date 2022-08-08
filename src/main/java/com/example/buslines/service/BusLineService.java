package com.example.buslines.service;

import com.example.buslines.api.client.SLApiClient;
import com.example.buslines.api.response.JourneyLineStopPoint;
import com.example.buslines.api.response.JourneyPatternStopResponse;
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
    //TODO put helpful comments everywhere once code is done
    private SLApiClient slApiClient;
    private BusLineUtils busLineUtils;

    public TopBusLinesScoreboard getTop10BusLines() {
        log.info("Fetching the bus line scoreboard");

        JourneyPatternStopResponse journeyResponse = slApiClient.getSlBusLines();
        ArrayList<JourneyLineStopPoint> stopPointList = journeyResponse.getJourneyResponseData().getJourneyLineStopPointList();
        //TODO if list is null or empty, stop execution
        Map<String, Set<String>> busLineToStopIdMap = busLineUtils.extractBusLineStopIdMap(stopPointList);

        return busLineUtils.createTopBusLinesScoreboardObject(busLineToStopIdMap);
    }
}