package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
public class TripInput {
    private Long destination;
    private String memo;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
