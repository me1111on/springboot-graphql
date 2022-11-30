package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public City(String name, String detail, OffsetDateTime createdAt, OffsetDateTime lastViewAt) {
        this.name = name;
        this.detail = detail;
        this.createdAt = createdAt;
        this.lastViewAt = lastViewAt;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "createdAt", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "lastViewAt", nullable = false)
    private OffsetDateTime lastViewAt;
}