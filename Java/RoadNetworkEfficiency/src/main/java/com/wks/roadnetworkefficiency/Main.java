package com.wks.roadnetworkefficiency;

public class Main {

    public static void main(String[] args) {
        final var dubai = new LatLngs(
                LatLng.valueOf("25.1549773,55.1322187"),
                LatLng.valueOf("25.1537422,55.190473")
        );

        try {
            final RoadNetworkEfficiency efficiency = new RoadNetworkEfficiencyService(new OpenStreetMapNavigationService())
                    .using(dubai);
            System.out.println(efficiency);
        } catch (RouteNotFoundException | SameStartingPointAndDestinationException e) {
            System.err.println(e.getMessage());
        }
    }
}
