package com.tundeadetunji.dispatchcontroller.business.utility;

import com.tundeadetunji.dispatchcontroller.business.entities.*;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import lombok.Data;

import static com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;

@Data
public class RegisterDroneValidator {

    private final DroneServiceImplementation service;

    public ErrorModel errorDuringDroneRegister(DroneToRegister droneToRegister) {

        ErrorsDuringDroneRegister model = new ErrorsDuringDroneRegister();
        boolean ok = true;

        //checking if serial is not blank/empty and within character-length limit
        String serial = droneToRegister.getSerial();
        if (serial.isEmpty() || serial.isBlank() || serial.length() > 100) {
            model.setSerial("Length of serial must be greater than 0 and not be more than 100.");
            ok = false;
        }

        //don't allow re-registering of the same serial
        if (service.findBySerial(serial) != null){
            model.setSerial("Drone with given serial already exist in the database");
            ok=false;
        }

        //checking if model is not blank/empty and valid
        String droneModel = droneToRegister.getModel();
        if (droneModel.isBlank() || droneModel.isEmpty() || (!(droneModel.equals(LIGHT_WEIGHT)) &&
                !(droneModel.equals(MIDDLE_WEIGHT)) &
                        !(droneModel.equals(CRUISER_WEIGHT)) &
                        !(droneModel.equals(HEAVY_WEIGHT)))) {
            model.setModel(String.format("Model cannot be blank and can only be %s, %s, %s or %s", LIGHT_WEIGHT, MIDDLE_WEIGHT, CRUISER_WEIGHT, HEAVY_WEIGHT));
            ok=false;
        }

        //checking if weight limit is not blank/empty and within acceptable range
        Double weightLimit = droneToRegister.getWeightLimit();
        if (weightLimit == null || weightLimit > 500 || weightLimit < 0) {
            model.setWeightLimit("Weight Limit cannot be null and must not be more than 500.00");
            ok=false;
        }

        ErrorModel result = new ErrorModel();
        result.setValid(ok);
        result.setDroneRegisterModel(model);
        return result;
    }


}

