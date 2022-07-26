package com.example.buslines.util;

import com.example.buslines.api.response.JourneyLineStopPoint;
import com.example.buslines.api.response.StopPointDetails;
import com.example.buslines.model.BusLine;
import com.example.buslines.model.LineDirectionPair;
import com.example.buslines.model.TopBusLinesScoreboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BusLineUtils {

    private final String DELIMITER = ";";


    /**
     * @param journeyLineStopPointList List of stop point details together with details about which bus line they belong to
     * @return Map where the key is the bus line identification string in format "lineNumber;directionCode" and the values are a set of unique stop IDs
     */
    public Map<String, Set<String>> extractBusLineStopIdMap(ArrayList<JourneyLineStopPoint> journeyLineStopPointList) {
        log.info("Extracting stop IDs");

        return journeyLineStopPointList.stream()
                .collect(
                        Collectors.groupingBy(stopPoint -> createLineDirectionKey(stopPoint.getLineNumber(), stopPoint.getDirectionCode()),
                                Collectors.mapping(JourneyLineStopPoint::getJourneyPatternPointNumber, Collectors.toSet()))
                );
    }

    /**
     * @param busLineToStopIdMap Map where the key is the bus identification string in format "lineNumber;directionCode" and the values are a set of unique stop IDs
     * @return Fully prepared object that will be consumed by frontend to construct the bus line scoreboard
     */
    public TopBusLinesScoreboard createTopBusLinesScoreboardObject(Map<String, Set<String>> busLineToStopIdMap, ArrayList<StopPointDetails> stopPointDetailsList) {
        log.info("Entering Top Scoreboard object creation");

        Map<String, Set<String>> topTenBusLines = busLineToStopIdMap.entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().size(), Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        LineDirectionPair lineDirectionPair;
        BusLine busLine;
        ArrayList<BusLine> busLines = new ArrayList<>();

        for (String key : topTenBusLines.keySet()) {
            lineDirectionPair = getLineDirectionPair(key);
            busLine = BusLine.builder()
                    .designation(lineDirectionPair.getLineNumber())
                    .direction(lineDirectionPair.getDirection())
                    .stopNames(getStopNamesFromStopIds(stopPointDetailsList, topTenBusLines.get(key)))
                    .noOfStops(topTenBusLines.get(key).size())
                    .build();

            busLines.add(busLine);
        }

        busLines.sort(Comparator.comparing(BusLine::getNoOfStops, Comparator.reverseOrder()));

        return TopBusLinesScoreboard.builder()
                .busLines(busLines)
                .build();
    }

    /**
     * @param stopPointDetailsList List of objects containing bus stop details
     * @param stopPointIdSet       Set of stop IDs for a specific bus line
     * @return Set of bus stop names
     */
    public Set<String> getStopNamesFromStopIds(ArrayList<StopPointDetails> stopPointDetailsList, Set<String> stopPointIdSet) {

        return stopPointDetailsList.stream()
                .filter(stopPointDetails -> stopPointIdSet.contains(stopPointDetails.getStopPointNumber()))
                .map(StopPointDetails::getStopPointName)
                .collect(Collectors.toSet());
    }

    public LineDirectionPair getLineDirectionPair(String lineDirectionKey) {
        String[] split = lineDirectionKey.split(DELIMITER);

        return LineDirectionPair.builder()
                .lineNumber(split[0])
                .direction(split[1])
                .build();
    }

    public String createLineDirectionKey(String line, String direction) {
        return line + DELIMITER + direction;
    }
}
