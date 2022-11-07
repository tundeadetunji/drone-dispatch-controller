package com.tundeadetunji.dispatchcontroller.business.utility;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;
import com.tundeadetunji.dispatchcontroller.business.entities.DroneToView;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import com.tundeadetunji.dispatchcontroller.business.entities.DroneToRegister;
import com.tundeadetunji.dispatchcontroller.business.entities.MedicationToLoad;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.services.MedicationServiceImplementation;

import java.util.List;

public class ModelMapping {

    public Drone fromDroneToRegister(DroneToRegister droneToRegister){
        Drone drone = new Drone();
        drone.setSerial(droneToRegister.getSerial());
        drone.setModel(droneToRegister.getModel());
        drone.setWeightLimit(droneToRegister.getWeightLimit());
        drone.setBatteryCapacity(100.00);
        drone.setState(State.IDLE.toString());
        return drone;
    }

    public Medication fromMedicationToLoad(MedicationToLoad medicationToLoad, final MedicationServiceImplementation medicationService){
        //if medication (name) already exists, (effectively only) add to the weight (though updates other properties in case they changed), else create new medication
        //but leave each load as a separate record (object) for easy user tracing
        if (medicationService.findByName(medicationToLoad.getName()) != null){
            Medication medication = medicationService.findByName(medicationToLoad.getName());
            medication.setCode(medicationToLoad.getCode());
            medication.setImage(medicationToLoad.getImage());
            medication.setName(medicationToLoad.getName());
            medication.setWeight(medicationToLoad.getWeight());
            medication.setDroneId(medicationToLoad.getDroneId());
            return medication;
        }
        else{
            Medication medication = new Medication();
            medication.setCode(medicationToLoad.getCode());
            medication.setImage(medicationToLoad.getImage());
            medication.setName(medicationToLoad.getName());
            medication.setWeight(medicationToLoad.getWeight());
            medication.setDroneId(medicationToLoad.getDroneId());
            return medication;
        }
    }

}
