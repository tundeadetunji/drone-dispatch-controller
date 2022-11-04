package com.tundeadetunji.dispatchcontroller.business.models;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String serial;

    @Column(nullable = false, length = 13)
    private String model;

    @Column(nullable = false)
    private Double weightLimit;

    @Column(nullable = false)
    private Double batteryCapacity;

    @Column(nullable = false, length = 10)
    private String state;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Medication> loadedMedication = new ArrayList<Medication>();

}
