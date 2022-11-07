package com.tundeadetunji.dispatchcontroller.business.utility;

import com.tundeadetunji.dispatchcontroller.business.entities.*;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Iterator;

import static com.tundeadetunji.dispatchcontroller.business.utility.Utility.codeIsValid;
import static com.tundeadetunji.dispatchcontroller.business.utility.Utility.nameIsValid;

@Data
public class LoadMedicationValidator {

    private final DroneServiceImplementation droneService;

    public ErrorModel errorDuringMedicationLoad(MedicationToLoad medication, Drone drone) {

        ErrorsDuringMedicationLoad model = new ErrorsDuringMedicationLoad();
        boolean ok = true;

        //checking if drone id is not blank/empty
        Long droneId = drone.getId();
        if (drone != null && !(droneId.toString().isEmpty()) && !(droneId.toString().isBlank())) {
            //checking if drone exists
            if (!(droneService.droneExists(drone.getId()))) {
                model.setDroneId("Drone Id is either null/blank or there's no match in the database");
                ok = false;
            }
        }

        //checking if name is not blank/empty
        String name = medication.getName();
        if (name.isBlank() || name.isEmpty() || !(nameIsValid(name))) {
            model.setName("Name cannot be null, cannot be empty and can only contain letters, numbers, - and _");
            ok = false;
        }

        //checking if code is not blank/empty
        String code = medication.getCode();
        if (code.isBlank() || code.isEmpty() || !(codeIsValid(code))) {
            model.setCode("Code cannot be null, cannot be empty and can only contain upper case letters, numbers and _");
            ok = false;
        }

        //checking if image is not blank/empty
        String image = medication.getImage();
        if (image.isBlank() || image.isEmpty()) {
            model.setImage("Image cannot be null or empty");
            ok = false;
        }

        //checking if battery capacity is above lower limit of 25
        if (drone.getBatteryCapacity() < 25) {
            model.setBatteryCapacity("Cannot load drone: battery capacity is below 25%");
            ok = false;
        }

        //checking if it can take this load, considering weight
        Collection<Medication> weights = drone.getLoadedMedication();
        Iterator<Medication> iterator = weights.iterator();
        double sum = 0.0;
        while (iterator.hasNext()) {
            sum += iterator.next().getWeight();
        }
        if (sum + medication.getWeight() > drone.getWeightLimit()) {
            model.setWeight(String.format("Weight of medication exceeds weight limit of drone, can only carry an additional %.2f grams", drone.getWeightLimit() - sum));
            ok = false;
        }


        ErrorModel result = new ErrorModel();
        result.setValid(ok);
        result.setMedicationLoadModel(model);
        return result;

    }

}

