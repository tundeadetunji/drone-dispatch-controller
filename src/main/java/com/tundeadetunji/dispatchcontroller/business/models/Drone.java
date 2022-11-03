package com.tundeadetunji.dispatchcontroller.business.models;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String serial;

    @Column(nullable = false, length = 13)
    private Model model;

    @Column(nullable = false)
    private Double weightLimit;

    @Column(nullable = false)
    private Double batteryCapacity;

    @Column(nullable = false, length = 10)
    private State state;
}
