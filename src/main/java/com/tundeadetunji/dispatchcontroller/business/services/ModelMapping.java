package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.DroneToRegister;

public class ModelMapping {
    public Drone FromDroneToRegister(final DroneToRegister droneToRegister){
        Drone drone = new Drone();
        drone.setSerial(droneToRegister.getSerial());
        drone.setModel(droneToRegister.getModel());
        drone.setWeightLimit(droneToRegister.getWeightLimit());
        drone.setBatteryCapacity(100.00);
        drone.setState(State.IDLE.toString());

        return drone;
    }
}
