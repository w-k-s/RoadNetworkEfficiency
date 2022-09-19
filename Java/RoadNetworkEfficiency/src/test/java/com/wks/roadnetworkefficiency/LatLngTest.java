package com.wks.roadnetworkefficiency;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LatLngTest {

    @Test
    public void GIVEN_aLatLngString_WHEN_parsed_THEN_latAndLngAreParsedCorrectly() {
        // GIVEN
        final var aLatLngString = "25.046169,55.2148843";

        // WHEN
        final var theLatLng = LatLng.valueOf(aLatLngString);

        // THEN
        assertEquals(new BigDecimal("25.046169"), theLatLng.latitude());
        assertEquals(new BigDecimal("55.2148843"), theLatLng.longitude());
    }

    @Test
    public void GIVEN_twoLatLngs_WHEN_absoluteDistanceIsCalculated_THEN_resultIsCorrect() {
        // GIVEN
        final var first = new LatLng(BigDecimal.valueOf(3L), BigDecimal.ZERO);
        final var second = new LatLng(BigDecimal.valueOf(0), BigDecimal.valueOf(4L));

        // WHEN
        final var absoluteDistance = first.absoluteValueDistanceTo(second);

        // THEN
        assertEquals(new Kilometers(new BigDecimal("777")), absoluteDistance);
    }

    @Test
    public void GIVEN_aLatLng_WHEN_printed_THEN_latAndLngAreFormattedToSixDecimalPlaces() {
        // GIVEN
        final var aLatLng = new LatLng(BigDecimal.valueOf(3L), BigDecimal.ZERO);

        // WHEN
        final var aLatLngString = aLatLng.toString();

        // THEN
        assertEquals("3.000000,0.000000", aLatLngString);
    }
}