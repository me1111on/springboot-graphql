package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
