package com.example.buslines.util;

import com.example.buslines.api.response.JourneyLineStopPoint;
import com.example.buslines.api.response.StopPointDetails;
import com.example.buslines.model.LineDirectionPair;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BusLineUtilsTest {

    BusLineUtils cut = new BusLineUtils(null); //decided to call the constructor instead of autowiring to skip unnecessary overhead since the slApiClient is not used in the tests (yet)

    @Test
    void extractBusLineStopIdMap_correctListProvided_successful() {
        // Arrange
        ArrayList<JourneyLineStopPoint> setupStopPointList = setupJourneyLineStopPointList();

        // Act
        Map<String, Set<String>> actual = cut.extractBusLineStopIdMap(setupStopPointList);

        // Assert
        assertThat(actual.keySet()).isEqualTo(setupStopPointList.stream().map(stopPoint -> cut.createLineDirectionKey(stopPoint.getLineNumber(), stopPoint.getDirectionCode())).collect(Collectors.toSet()));
        assertThat(actual.get("1;1")).containsOnly("journeyPatID1", "journeyPatID2");
    }


    @Test
    void extractBusLineStopIdMap_nullProvided_throwsNPE() {
        assertThrows(NullPointerException.class, () -> cut.extractBusLineStopIdMap(null));
    }

    @Test
    @Disabled
    void extractBusLineStopIdMap_emptyProvided_throwsException() { //TODO change later to proprietary exception
        assertThrows(Exception.class, () -> cut.extractBusLineStopIdMap(new ArrayList<>()));
    }

    @Test
    void getLineDirectionPair_correctKeyFormat_successful() {
        // Arrange
        String expectedLine = "123";
        String expectedDir = "1";
        String lineDirKey = expectedLine + ";" + expectedDir;

        // Act
        LineDirectionPair actual = cut.getLineDirectionPair(lineDirKey);

        // Assert
        assertThat(actual.getLineNumber()).isEqualTo(expectedLine);
        assertThat(actual.getDirection()).isEqualTo(expectedDir);
    }

    @Test
    void getStopNamesFromStopIds_happyFlow_successful() {
        // Arrange
        ArrayList<StopPointDetails> setupStopPointDetailsList = setupStopPointDetailsList();
        Set<String> expected = Set.of("stopA", "stopB");

        // Act
        Set<String> actual = cut.getStopNamesFromStopIds(setupStopPointDetailsList, Set.of("1", "2"));

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    private ArrayList<StopPointDetails> setupStopPointDetailsList() {
        StopPointDetails stopADetails = StopPointDetails.builder() // the Builder is used here for readability since there is a few fields needed for the setup but quite a lot of fields in the model itself
                .stopPointName("stopA")
                .stopPointNumber("1")
                .build();

        StopPointDetails stopBDetails = StopPointDetails.builder()
                .stopPointName("stopB")
                .stopPointNumber("2")
                .build();

        ArrayList<StopPointDetails> stopPointDetailsSetup = new ArrayList<>();
        stopPointDetailsSetup.add(stopADetails);
        stopPointDetailsSetup.add(stopBDetails);

        return stopPointDetailsSetup;
    }

    private ArrayList<JourneyLineStopPoint> setupJourneyLineStopPointList() {
        String now = LocalDateTime.now().toString();
        ArrayList<JourneyLineStopPoint> journeyLineStopPointsListSetup = new ArrayList<>();

        journeyLineStopPointsListSetup.add(new JourneyLineStopPoint("1", "1", "journeyPatID1", now, now)); // constructor is used here instead of Builder to keep the code shorter and more readable
        journeyLineStopPointsListSetup.add(new JourneyLineStopPoint("1", "1", "journeyPatID2", now, now)); // since the dates are not used in any logic at this point, the LocalDateTime values don't matter in this initialization
        journeyLineStopPointsListSetup.add(new JourneyLineStopPoint("2", "1", "journeyPatID2", now, now));
        journeyLineStopPointsListSetup.add(new JourneyLineStopPoint("3", "1", "journeyPatID3", now, now));

        return journeyLineStopPointsListSetup;
    }
}