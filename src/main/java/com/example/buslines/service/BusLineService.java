package com.example.buslines.service;

import com.example.buslines.api.client.SLApiClient;
import com.example.buslines.model.JourneyResponse;
import com.example.buslines.model.LineDirectionPair;
import com.example.buslines.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        ArrayList<Result> stopPointList = journeyResponse.getResponseData().getResult();
        //TODO create a busline-stopId list map
        Map<String, Set<String>> busLineToStopIdMap = extractBusLineStopIdMap(stopPointList);
        Map<String, Long> topTenLongestBusLinesMap = getTopTenLongestBusLinesMap(stopPointList);

        //TODO extract the relevant busLine stops and provide in method createTopBusLinesScoreboardObject
        createTopBusLinesScoreboardObject(topTenLongestBusLinesMap);
    }

    private Map<String, Set<String>> extractBusLineStopIdMap(ArrayList<Result> stopPointList) {
        //TODO replace repeated usages of unique ID creation
        return stopPointList.stream()
                .collect(
                        Collectors.groupingBy(stopPoint -> stopPoint.getLineNumber() + DELIMITER + stopPoint.getDirectionCode(),
                                Collectors.mapping(Result::getJourneyPatternPointNumber, Collectors.toSet()))
                );
    }

    private void createTopBusLinesScoreboardObject(Map<String, Long> topBusLinesMap) {
        Long noOfStops;
        LineDirectionPair ldPair;
        for (String key : topBusLinesMap.keySet()) {
            noOfStops = topBusLinesMap.get(key);
            ldPair = getLineDirectionPair(key);

        }

    }

    private LineDirectionPair getLineDirectionPair(String lineDirectionKey) {
        String[] split = lineDirectionKey.split(DELIMITER);

        return LineDirectionPair.builder()
                .lineNumber(split[0])
                .direction(split[1])
                .build();
    }

    private Map<String, Long> getTopTenLongestBusLinesMap(ArrayList<Result> stopPointList) { //TODO see if the steps in this method can be done in a oneliner
        Map<String, Long> maxLineStopList = stopPointList.stream().collect(Collectors.groupingBy(result -> result.lineNumber + DELIMITER + result.directionCode, Collectors.counting()));

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(maxLineStopList.entrySet());

        return sortedEntries.stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

    }
}
