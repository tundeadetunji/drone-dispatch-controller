package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.Medication;

import java.util.List;

public interface DroneService {
    Drone saveDrone(Drone drone);
    boolean droneExists(Long id);
    Drone findById(Long id);
    boolean addMedicationToDrone(Medication medication, Drone drone);
    Drone findBySerial(String serial);
    List<Drone> findAll();
}
