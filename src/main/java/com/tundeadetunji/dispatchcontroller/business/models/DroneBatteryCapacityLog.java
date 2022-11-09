package com.tundeadetunji.dispatchcontroller.business.models;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Data
@Entity
public class DroneBatteryCapacityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long droneId;

    @Column(nullable = false)
    private Double batteryCapacity;

    @Column(nullable = false, length = 10)
    private String state;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private String loggedAt = new SimpleDateFormat("mm.dd.yyyy-hh:mm:ss").format(System.currentTimeMillis());

}
