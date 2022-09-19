package com.wks.roadnetworkefficiency;

public interface NavigationService {
    Kilometers drivingDistanceBetween(LatLng first, LatLng second) throws RouteNotFoundException;
}
