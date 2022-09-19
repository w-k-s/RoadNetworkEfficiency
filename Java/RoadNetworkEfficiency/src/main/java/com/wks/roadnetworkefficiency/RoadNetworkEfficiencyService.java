package com.wks.roadnetworkefficiency;

public class RoadNetworkEfficiencyService {

    private final NavigationService navigationService;

    public RoadNetworkEfficiencyService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    RoadNetworkEfficiency using(LatLngs points) throws RouteNotFoundException, SameStartingPointAndDestinationException {
        final Kilometers absoluteValueDistance = points.first().absoluteValueDistanceTo(points.second());
        final var drivingDistance = navigationService.drivingDistanceBetween(points.first(), points.second());
        return RoadNetworkEfficiency.of(drivingDistance, absoluteValueDistance);
    }
}
