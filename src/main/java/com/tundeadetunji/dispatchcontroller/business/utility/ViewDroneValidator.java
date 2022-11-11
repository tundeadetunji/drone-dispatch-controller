package com.tundeadetunji.dispatchcontroller.business.utility;

import com.tundeadetunji.dispatchcontroller.business.entities.DroneToView;
import com.tundeadetunji.dispatchcontroller.business.entities.ErrorModel;
import com.tundeadetunji.dispatchcontroller.business.entities.ErrorsDuringViewDrone;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import lombok.Data;

/**
 * This is the logic that is used to trap invalid/incomplete
 * values the user enters when attempting to view information
 * about a drone.
 * The feedback is used to build the ErrorModel object
 * that is later passed back to the user.
 */

@Data
public class ViewDroneValidator {
    private final DroneServiceImplementation droneService;

    public ErrorModel errorDuringViewDrone(DroneToView drone) {

        ErrorsDuringViewDrone model = new ErrorsDuringViewDrone();
        boolean ok = true;

        //the id is needed to view the drone info

        //checking if drone exists by supplied id
        Long id = drone.getId();
        if (droneService.droneExists(id) == false) {
            model.setId("Id wasn't supplied or there's no match in the database");
            ok = false;
        }

        ErrorModel result = new ErrorModel();
        result.setValid(ok);
        result.setViewDroneModel(model);
        return result;

    }

}
