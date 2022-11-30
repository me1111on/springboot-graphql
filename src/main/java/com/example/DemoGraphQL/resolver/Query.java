package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.*;
import com.example.DemoGraphQL.repository.CityRepository;
import com.example.DemoGraphQL.repository.TripRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Query implements GraphQLQueryResolver {

    private CityRepository cityRepository;
    private TripRepository tripRepository;


    public Query(CityRepository cityRepository, TripRepository tripRepository) {

        this.cityRepository = cityRepository;
        this.tripRepository = tripRepository;
    }


    public Trip getTrip(Long id) {
        return tripRepository.findById(id).orElseThrow(null);
    }

//    public Iterable<City> getA() {
//        Spliterator<Trip> trips = tripRepository.findBystartDateBefore(OffsetDateTime.now()).spliterator();
//        List<City> t = StreamSupport.stream(trips, false).filter(trip -> {
//            return trip.getEndDate().isAfter(OffsetDateTime.now());
//        }).map(trip -> {
//            return trip.getDestination();
//        }).limit(10).collect(Collectors.toList());
//        return t;
//    }
}
