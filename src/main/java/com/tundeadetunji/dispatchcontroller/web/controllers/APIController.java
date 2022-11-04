package com.tundeadetunji.dispatchcontroller.web.controllers;

import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.entities.DroneToRegister;
import com.tundeadetunji.dispatchcontroller.business.entities.MedicationToLoad;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.services.MedicationServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.utility.MedicationValidator;
import com.tundeadetunji.dispatchcontroller.business.utility.ModelMapping;
import com.tundeadetunji.dispatchcontroller.business.entities.ErrorModel;
import com.tundeadetunji.dispatchcontroller.business.utility.DroneValidator;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Data
public class APIController {

    private final DroneServiceImplementation droneService;
    private final MedicationServiceImplementation medicationService;

    @GetMapping("/")
    public ResponseEntity<Object> welcome() {
        return ResponseEntity.ok().body("done");
    }

    @PostMapping("/register")
    public ResponseEntity<Object> RegisterDrone(@RequestBody DroneToRegister droneToRegister) {
        DroneValidator validator = new DroneValidator(droneService);
        ErrorModel model = validator.errorDuringDroneRegister(droneToRegister);
        if (model.isValid()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(droneService.saveDrone(new ModelMapping().fromDroneToRegister(droneToRegister)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> getDrones(){
        return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findAll());
    }


    @PostMapping("/load")
    public ResponseEntity<Object> LoadDrone(@RequestBody MedicationToLoad medication) {
        Drone drone = droneService.findById(medication.getDroneId());
        MedicationValidator validator = new MedicationValidator(droneService);
        ErrorModel model = validator.errorDuringMedicationLoad(medication, drone);
        if (model.isValid()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(droneService.addMedicationToDrone(new ModelMapping().fromMedicationToLoad(medication, medicationService), drone));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


}
