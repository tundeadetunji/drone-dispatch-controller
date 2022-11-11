package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

/**
 * This is the object that is given by the user when registering
 * a new drone.
 * Other information about the drone like state and battery capacity
 * are added automatically with default values
 */

@Data
public class DroneToRegister {
    private String serial;
    private String model;
    private Double weightLimit;
}
