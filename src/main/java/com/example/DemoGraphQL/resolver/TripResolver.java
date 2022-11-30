package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.City;
import com.example.DemoGraphQL.model.Trip;
import com.example.DemoGraphQL.repository.CityRepository;
import graphql.kickstart.tools.GraphQLResolver;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TripResolver implements GraphQLResolver<Trip> {

    private CityRepository cityRepository;

    public TripResolver(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City getDestination(Trip trip) {
        return cityRepository.findById(trip.getDestination()
                        .getId())
                .orElseThrow(null);
    }}