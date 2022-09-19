package com.wks.roadnetworkefficiency;

import com.wks.roadnetworkefficiency.exceptions.SameStartingPointAndDestinationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record RoadNetworkEfficiency(BigDecimal value) {
    static RoadNetworkEfficiency of(Kilometers drivingDistance, Kilometers absoluteValueDistance) throws SameStartingPointAndDestinationException {
        if (absoluteValueDistance.isZero()){
            throw new SameStartingPointAndDestinationException("Can not calculate efficiency when starting point and destination are the same");
        }

        final var value = drivingDistance.value().divide(absoluteValueDistance.value(), RoundingMode.HALF_UP);
        return new RoadNetworkEfficiency(value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
