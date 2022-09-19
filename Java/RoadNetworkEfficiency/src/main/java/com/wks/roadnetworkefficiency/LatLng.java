package com.wks.roadnetworkefficiency;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public record LatLng(BigDecimal latitude, BigDecimal longitude) {
    public LatLng {
        Objects.requireNonNull(latitude);
        Objects.requireNonNull(longitude);
    }

    static LatLng valueOf(String latLngString) {
        try {
            final var decimals = Arrays.stream(latLngString.split(","))
                    .map(BigDecimal::new)
                    .toList();
            return new LatLng(decimals.get(0), decimals.get(1));
        } catch (Exception e) {
            throw new IllegalArgumentException("LatLng.valueOf expects two decimal numbers separated by a comma.");
        }
    }

    /**
     * NOTW: THIS IS NOT AN ACCURATE WAY TO CALCULATE THIS DISTANCE
     *
     * @return
     */
    Kilometers absoluteValueDistanceTo(LatLng point) {
        final var x = this.latitude.subtract(point.latitude()).abs();
        final var y = this.longitude.subtract(point.longitude()).abs();
        // Each degree of lat/lng is rougly 111km
        return Kilometers.of(x.add(y).multiply(BigDecimal.valueOf(111L)));
    }

    @Override
    public String toString() {
        return String.format("%.6f,%.6f", latitude, longitude);
    }
}
