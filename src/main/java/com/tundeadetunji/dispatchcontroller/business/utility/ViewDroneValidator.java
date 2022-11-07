package com.tundeadetunji.dispatchcontroller.business.utility;

import com.tundeadetunji.dispatchcontroller.business.entities.DroneToView;
import com.tundeadetunji.dispatchcontroller.business.entities.ErrorModel;
import com.tundeadetunji.dispatchcontroller.business.entities.ErrorsDuringViewDrone;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import lombok.Data;

@Data
public class ViewDroneValidator {
    private final DroneServiceImplementation droneService;

    public ErrorModel errorDuringViewDrone(DroneToView drone) {

        ErrorsDuringViewDrone model = new ErrorsDuringViewDrone();
        boolean ok = true;

        //the id is needed to view the drone info

        //checking if id was supplied
        Long id = drone.getId();
        if (String.valueOf(id).length() > 0) {
            //checking if drone exists by id
            if (droneService.findById(id) == null){
                model.setId("Id wasn't supplied or there's no match in the database");
                ok = false;
            }
        }

        ErrorModel result = new ErrorModel();
        result.setValid(ok);
        result.setViewDroneModel(model);
        return result;

    }

}
