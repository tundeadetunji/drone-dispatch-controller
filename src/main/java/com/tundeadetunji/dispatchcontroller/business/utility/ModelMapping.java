package com.tundeadetunji.dispatchcontroller.business.utility;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;
import com.tundeadetunji.dispatchcontroller.business.entities.*;
import com.tundeadetunji.dispatchcontroller.business.models.DroneBatteryCapacityLog;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.services.MedicationServiceImplementation;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This file contains logic to convert objects, specifically from
 * the objects in the entities package to the objects in the
 * models package.
 */

@Slf4j
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
            medication.setCode(medicationToLoad.getCode().toUpperCase());
            medication.setImage(medicationToLoad.getImage());
            medication.setName(medicationToLoad.getName());
            medication.setWeight(medicationToLoad.getWeight());
            medication.setDroneId(medicationToLoad.getDroneId());
            return medication;
        }
        else{
            Medication medication = new Medication();
            medication.setCode(medicationToLoad.getCode().toUpperCase());
            medication.setImage(medicationToLoad.getImage());
            medication.setName(medicationToLoad.getName());
            medication.setWeight(medicationToLoad.getWeight());
            medication.setDroneId(medicationToLoad.getDroneId());
            return medication;
        }
    }

    public DroneBatteryCapacityToReturn fromDroneBatterCapacityToReturn(DroneToView droneToView, DroneServiceImplementation droneService){
        DroneBatteryCapacityToReturn model = new DroneBatteryCapacityToReturn();
        Drone drone = droneService.findById(droneToView.getId());
        model.setId(drone.getId());
        model.setSerial(drone.getSerial());
        model.setBatteryCapacity(drone.getBatteryCapacity());
        return model;
    }

    public List<MedicationToReturn> fromMedicationToReturn(Collection<Medication> medications){
        //removing droneId from each Medication object
        //just to show the Medication without where it's loaded
        List<MedicationToReturn> list = new ArrayList<MedicationToReturn>();
        Iterator<Medication> iterator = medications.iterator();
        while(iterator.hasNext()){
            Medication med = iterator.next();
            MedicationToReturn medication = new MedicationToReturn();
            medication.setId(med.getId());
            medication.setName(med.getName());
            medication.setWeight(med.getWeight());
            medication.setCode(med.getCode());
            medication.setImage(med.getImage());
            list.add(medication);
        }
        return list;
    }

    public List<DroneBatteryCapacityLog> fromDroneBatteryCapacityLog(List<Drone> drones){
        List<DroneBatteryCapacityLog> models = new ArrayList<DroneBatteryCapacityLog>();
        Iterator<Drone> iterator = drones.iterator();
        while(iterator.hasNext()){
            Drone drone = iterator.next();

            //getting the sum of all weights of loaded medication
            List<Medication> medications = (List<Medication>) drone.getLoadedMedication();
            Double weight = 0.0;
            List<Double> weights = new ArrayList<>();
            medications.stream().forEach(m -> weights.add(m.getWeight()));
            Iterator<Double> weightsIterator = weights.iterator();
            while(weightsIterator.hasNext()){
                weight += Double.parseDouble(String.valueOf(weightsIterator.next()));
            }

            DroneBatteryCapacityLog model = new DroneBatteryCapacityLog();
            model.setDroneId(drone.getId());
            model.setState(drone.getState());
            model.setWeight(weight);
            model.setBatteryCapacity(drone.getBatteryCapacity());

            models.add(model);
        }

        return models;
    }
}
