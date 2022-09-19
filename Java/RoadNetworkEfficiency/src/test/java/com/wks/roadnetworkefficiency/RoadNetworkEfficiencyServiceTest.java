package com.wks.roadnetworkefficiency;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoadNetworkEfficiencyServiceTest {

    private static final MockWebServer mockServer = new MockWebServer();

    @BeforeAll
    public static void setup() {
        try {
            mockServer.start(4509);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GIVEN_twoLatLngs_WHEN_actualDistanceSuccessfullyLoaded_THEN_roadNetworkEfficiencyIsCalculated() throws Throwable {
        // GIVEN
        final var latLngs = new LatLngs(
                LatLng.valueOf("25.1549773,55.1322187"),
                LatLng.valueOf("25.1537422,55.190473")
        );

        // WHEN
        mockServer.enqueue(new MockResponse().setBody(Files.readString(Paths.get(getClass().getResource("/SampleResponses/SuccessResponse.json").toURI()))));
        final var navigationService = new OpenStreetMapNavigationService("http", "localhost", "" + mockServer.getPort());
        final var roadNetworkEfficiencyService = new RoadNetworkEfficiencyService(navigationService);

        // THEN
        assertEquals(new RoadNetworkEfficiency(new BigDecimal("2.9")), roadNetworkEfficiencyService.using(latLngs));
    }

    @AfterAll
    public static void tearDown() {
        try {
            mockServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
