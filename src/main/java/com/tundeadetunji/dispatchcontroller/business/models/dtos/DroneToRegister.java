package com.tundeadetunji.dispatchcontroller.business.models.dtos;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.Model;
import com.tundeadetunji.dispatchcontroller.business.domain.Domain.State;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DroneToRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String serial;

    @Column(nullable = false, length = 13)
    private String model;

    @Column(nullable = false)
    private Double weightLimit;
}
