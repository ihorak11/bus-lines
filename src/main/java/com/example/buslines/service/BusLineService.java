package com.example.buslines.service;

import com.example.buslines.api.client.SLApiClient;
import com.example.buslines.model.JourneyResponse;
import com.example.buslines.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BusLineService {
    //TODO put helpful comments everywhere once code is done

    private SLApiClient slApiClient;

    public void getTop10BusLines() {
        JourneyResponse journeyResponse = slApiClient.getSlBusLines();
        ArrayList<Result> stopPointList = journeyResponse.getResponseData().getResult();
        Map<String, Long> topTenLongestBusLinesMap = getTopTenLongestBusLinesMap(stopPointList);
        System.out.println();
    }

    private Map<String, Long> getTopTenLongestBusLinesMap(ArrayList<Result> stopPointList) { //TODO see if the steps in this method can be done in a oneliner
        Map<String, Long> maxLineStopList = stopPointList.stream().collect(Collectors.groupingBy(result -> result.lineNumber + ";" + result.directionCode, Collectors.counting()));

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(maxLineStopList.entrySet());

        return sortedEntries.stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

    }
}
