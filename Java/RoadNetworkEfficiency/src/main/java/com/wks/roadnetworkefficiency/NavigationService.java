package com.wks.roadnetworkefficiency;

import com.wks.roadnetworkefficiency.exceptions.RouteNotFoundException;

public interface NavigationService {
    Kilometers drivingDistanceBetween(LatLng first, LatLng second) throws RouteNotFoundException;
}
