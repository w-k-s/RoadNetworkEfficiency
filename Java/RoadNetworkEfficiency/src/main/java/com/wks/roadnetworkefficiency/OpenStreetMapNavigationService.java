package com.wks.roadnetworkefficiency;

import com.wks.roadnetworkefficiency.exceptions.InternalException;
import com.wks.roadnetworkefficiency.exceptions.RouteNotFoundException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

public class OpenStreetMapNavigationService implements NavigationService {

    private final String scheme;
    private final String host;
    private final String port;

    public OpenStreetMapNavigationService() {
        this("http", "router.project-osrm.org", "80");
    }

    public OpenStreetMapNavigationService(final String scheme, final String host, final String port) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

    @Override
    public Kilometers drivingDistanceBetween(LatLng first, LatLng second) throws RouteNotFoundException {

        try {
            var coordinates = new LatLngs(first, second).toString();
            var request = HttpRequest.newBuilder()
                    .uri(new URI("%s://%s:%s/route/v1/driving/%s".formatted(this.scheme, this.host, this.port, coordinates)))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();

            var jsonResponse = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString())
                    .body();

            var json = new JSONObject(jsonResponse);
            return Optional.ofNullable(json.optJSONArray("routes"))
                    .map(it -> it.optJSONObject(0))
                    .map(it -> it.optBigDecimal("distance", BigDecimal.ZERO))
                    .map(it -> it.divide(new BigDecimal("1000"), RoundingMode.HALF_UP))
                    .map(Kilometers::new)
                    .orElseThrow(RouteNotFoundException::new);

        } catch (URISyntaxException e) {
            throw new InternalException("Invalid URI Syntax", e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to load route", e);
        }
    }
}
