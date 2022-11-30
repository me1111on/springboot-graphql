package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="city_id", nullable = false, updatable = false)
    private City destination;

    @Column(name="memo", nullable = false)
    private String memo;

    @Column(name="startDate", nullable = false)
    private OffsetDateTime startDate;

    @Column(name="endDate", nullable = false)
    private OffsetDateTime endDate;

    public Trip(City destination, String memo, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.destination = destination;
        this.memo = memo;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
