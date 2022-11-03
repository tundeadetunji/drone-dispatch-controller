package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain.*;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.DroneToRegister;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.ErrorDuringRegister;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.RegisterErrorModel;

public class Validation {
    private String MIDDLE_WEIGHT = "Middleweight";
    private String CRUISER_WEIGHT = "Cruiserweight";
    private String HEAVY_WEIGHT = "Heavyweight";
    private String LIGHT_WEIGHT = "Lightweight";

    public RegisterErrorModel errorDuringRegister(DroneToRegister droneToRegister) {
        if (droneToRegister == null) {
            RegisterErrorModel model = new RegisterErrorModel();
            model.setMessage("No information supplied!");
            model.setModel(null);
            model.setValid(false);
            return model;
        }

        ErrorDuringRegister model = new ErrorDuringRegister();
        boolean ok = true;
        String message = "One or more details was not supplied or is invalid!";

        if (droneToRegister.getSerial().isEmpty() || droneToRegister.getSerial().isBlank() || droneToRegister.getSerial().length() > 100) {
            model.setSerial("Length of serial must be greater than 0 and not be more than 100.");
            ok = false;
        }

        if (droneToRegister.getModel().isBlank() || droneToRegister.getModel().isEmpty() || (!(droneToRegister.getModel().equals("Lightweight")) &&
                !(droneToRegister.getModel().equals("Middleweight")) &
                        !(droneToRegister.getModel().equals("Cruiserweight")) &
                        !(droneToRegister.getModel().equals("Heavyweight")))) {
            model.setModel(String.format("Model cannot be blank and can only be {%s}, {%s}, {%s} or {%s}", "Lightweight", "Middleweight", "Cruiserweight", "Heavyweight"));
            ok = false;
        }

        if (droneToRegister.getWeightLimit() == null || droneToRegister.getWeightLimit() > 500 || droneToRegister.getWeightLimit() < 0) {
            model.setWeightLimit("Weight Limit cannot be null and must not be more than 500.00");
            ok = false;
        }


        RegisterErrorModel result = new RegisterErrorModel();
        result.setValid(ok);
        result.setMessage(message);
        result.setModel(model);
        return result;
    }
}

