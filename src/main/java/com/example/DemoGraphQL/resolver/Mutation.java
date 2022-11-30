package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.*;
import com.example.DemoGraphQL.repository.CityRepository;
import com.example.DemoGraphQL.repository.TripRepository;
import graphql.GraphQLException;
import graphql.GraphqlErrorException;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {
    private CityRepository cityRepository;
    private TripRepository tripRepository;
    public Mutation(CityRepository cityRepository, TripRepository tripRepository) {
        this.cityRepository = cityRepository;
        this.tripRepository = tripRepository;
    }

    public City createCity(CityInput input){
        City city = new City();
        city.setName(input.getName());
        city.setDetail(input.getDetail());
        city.setCreatedAt(OffsetDateTime.now());
        city.setLastViewAt(OffsetDateTime.now());
        cityRepository.save(city);
        return city;
    }

    public Boolean deleteCity(Long id){
        try {
            cityRepository.deleteById(id);
        }catch( Exception e){
            return false;
        }
        return true;
    }

    public City getCity(Long id) {
        Optional<City> city = cityRepository.findById(id);
        if(city.isPresent()){
            city.get().setLastViewAt(OffsetDateTime.now());
            cityRepository.save(city.get());
        }
        throw new GraphQLException("City Not Found");
    }

    public City editCity(Long id, CityInput input){
        Optional<City> opt = cityRepository.findById(id);
        if(opt.isPresent()) {
            City city = opt.get();
            if (input.getName() != null) {
                city.setName(input.getName());
            }
            if (input.getDetail() != null) {
                city.setDetail(input.getDetail());
            }
            cityRepository.save(city);
            return city;
        }
        throw new GraphQLException("City Not found");
    }

    public Trip createTrip(TripInput input){
        Trip trip = new Trip();
        Optional<City> opt = cityRepository.findById(input.getDestination());
        if (opt.isPresent()){
            trip.setDestination(opt.get());
        }
        trip.setMemo(input.getMemo());
        trip.setStartDate(input.getStartDate());
        trip.setEndDate(input.getEndDate());

        if (trip.getEndDate().isBefore(OffsetDateTime.now())){
            throw new GraphQLException("EndDate Must be after Now");
        }
        if (trip.getEndDate().isBefore(trip.getStartDate())){
            throw new GraphQLException("EndDate Must be after StartDate");
        }

        tripRepository.save(trip);
        return trip;
    }

    public Boolean deleteTrip(Long id){
        try {
            tripRepository.deleteById(id);
        }catch( Exception e){
            return false;
        }
        return true;
    }

    public Trip editTrip(Long id, TripInput input){
        Optional<Trip> opt = tripRepository.findById(id);
        if (opt.isPresent()){
            Trip trip = opt.get();
            if (input.getDestination() != null) {
                Optional<City> city = cityRepository.findById(input.getDestination());
                if (city.isPresent()) {
                    trip.setDestination(city.get());
                }else{
                    throw new GraphQLException("Destination Not Found");
                }
            }
            if (input.getMemo() != null) {
                trip.setMemo(input.getMemo());
            }

            if (input.getStartDate() != null){
                trip.setStartDate(input.getStartDate());
            }
            if (input.getEndDate() != null){
                trip.setEndDate(input.getEndDate());
            }

            if (!trip.getStartDate().isBefore(trip.getEndDate())){
                throw new GraphQLException("StartDate must be before EndDate");
            }
            tripRepository.save(trip);
            return trip;

        }
        throw new GraphQLException("Trip ID Not Found!");
    }
}
