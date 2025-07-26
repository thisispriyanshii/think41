package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "distribution_centers")
public class DistributionCenter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;

    // Getters and Setters
}
