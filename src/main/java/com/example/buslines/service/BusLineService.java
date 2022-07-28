package com.example.buslines.service;

import com.example.buslines.api.client.SLApiClient;
import com.example.buslines.model.JourneyLineStopPoint;
import com.example.buslines.model.JourneyResponse;
import com.example.buslines.model.LineDirectionPair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BusLineService {
    //TODO put helpful comments everywhere once code is done
    //TODO put statement stating that this webapp contains info provided by Trafiklab API (part of the terms and conditions)
    private SLApiClient slApiClient;

    private final String DELIMITER = ";";

    public void getTop10BusLines() {
        JourneyResponse journeyResponse = slApiClient.getSlBusLines();
        ArrayList<JourneyLineStopPoint> stopPointList = journeyResponse.getResponseData().getJourneyLineStopPointList();
        Map<String, Set<String>> busLineToStopIdMap = extractBusLineStopIdMap(stopPointList);

        createTopBusLinesScoreboardObject(busLineToStopIdMap);
    }

    private Map<String, Set<String>> extractBusLineStopIdMap(ArrayList<JourneyLineStopPoint> journeyLineStopPointList) {
        //TODO replace repeated usages of unique ID creation
        return journeyLineStopPointList.stream()
                .collect(
                        Collectors.groupingBy(stopPoint -> stopPoint.getLineNumber() + DELIMITER + stopPoint.getDirectionCode(),
                                Collectors.mapping(JourneyLineStopPoint::getJourneyPatternPointNumber, Collectors.toSet()))
                );
    }

    private void createTopBusLinesScoreboardObject(Map<String, Set<String>> busLineToStopIdMap) { //TODO change return type
        //TODO sort and take top 10 bus lines
        Map<String, Set<String>> topTenBusLines = busLineToStopIdMap.entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().size(), Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); //TODO fix sorting
        //TODO call API for fetching line names
        //TODO iterate top 10 bus lines and find names for bus stops
        //TODO create BusLine object and put in scoreboard
        //TODO pay attention that it is ordered in the response object
    }

    private LineDirectionPair getLineDirectionPair(String lineDirectionKey) {
        String[] split = lineDirectionKey.split(DELIMITER);

        return LineDirectionPair.builder()
                .lineNumber(split[0])
                .direction(split[1])
                .build();
    }

    private Map<String, Integer> getTopTenLongestBusLinesMap(Map<String, Set<String>> stopPointList) { //TODO see if the steps in this method can be done in a oneliner
        Map<String, Integer> busLineToCountedStopsMap = stopPointList.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));

        return busLineToCountedStopsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
