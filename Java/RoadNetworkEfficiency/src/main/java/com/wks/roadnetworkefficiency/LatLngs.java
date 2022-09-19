package com.wks.roadnetworkefficiency;

import java.util.Objects;

public record LatLngs(LatLng first, LatLng second) {
    public LatLngs {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
    }

    @Override
    public String toString() {
        return String.format("%s;%s", first, second);
    }
}
