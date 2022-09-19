package com.wks.roadnetworkefficiency;

import java.math.BigDecimal;
import java.util.Objects;

public record Kilometers(BigDecimal value) implements Comparable<Kilometers> {
    static Kilometers of(BigDecimal value) {
        return new Kilometers(value);
    }

    public boolean isZero() {
        return this.value.compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public int compareTo(Kilometers o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kilometers that = (Kilometers) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
