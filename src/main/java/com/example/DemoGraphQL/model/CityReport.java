package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityReport {
    private Iterable<City> ongoingCities;
    private Iterable<City> planedCities;
    private Iterable<City> recentAssignedCities;
    private Iterable<City> recentViewedCities;
    private Iterable<City> otherCities;
}
