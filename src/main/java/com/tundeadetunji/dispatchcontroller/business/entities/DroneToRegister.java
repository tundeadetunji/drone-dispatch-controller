package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

@Data
public class DroneToRegister {
    private String serial;
    private String model;
    private Double weightLimit;
}
