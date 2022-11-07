package com.tundeadetunji.dispatchcontroller.web.controllers;

import com.tundeadetunji.dispatchcontroller.business.entities.DroneToView;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.entities.DroneToRegister;
import com.tundeadetunji.dispatchcontroller.business.entities.MedicationToLoad;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.services.MedicationServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.utility.LoadMedicationValidator;
import com.tundeadetunji.dispatchcontroller.business.utility.ModelMapping;
import com.tundeadetunji.dispatchcontroller.business.entities.ErrorModel;
import com.tundeadetunji.dispatchcontroller.business.utility.RegisterDroneValidator;
import com.tundeadetunji.dispatchcontroller.business.utility.ViewDroneValidator;
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

    //todo
    //return all api endpoints urls
    @GetMapping("/")
    public ResponseEntity<Object> welcome() {
        return ResponseEntity.ok().body("done");
    }


    //register a drone
    @PostMapping("/register")
    public ResponseEntity<Object> RegisterDrone(@RequestBody DroneToRegister droneToRegister) {
        RegisterDroneValidator validator = new RegisterDroneValidator(droneService);
        ErrorModel model = validator.errorDuringDroneRegister(droneToRegister);
        if (model.isValid()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(droneService.saveDrone(new ModelMapping().fromDroneToRegister(droneToRegister)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    //view all drones
    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> getDrones(){
        return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findAll());
    }


    //view a drone, by id
    @GetMapping("/drone")
    public ResponseEntity<Object> getDrone(@RequestBody DroneToView drone)
    {
        ViewDroneValidator validator = new ViewDroneValidator(droneService);
        ErrorModel model = validator.errorDuringViewDrone(drone);
        if (model.isValid()){
            return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findById(drone.getId()));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }

    //view a drone's list of (loaded) medication
    @GetMapping("/loaded")
    public ResponseEntity<Object> checkLoadedMedication(@RequestBody DroneToView drone){
        ViewDroneValidator validator = new ViewDroneValidator(droneService);
        ErrorModel model = validator.errorDuringViewDrone(drone);
        if (model.isValid()){
            return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findById(drone.getId()).getLoadedMedication());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    //load a drone with medication item
    @PostMapping("/load")
    public ResponseEntity<Object> LoadDrone(@RequestBody MedicationToLoad medication) {
        Drone drone = droneService.findById(medication.getDroneId());
        LoadMedicationValidator validator = new LoadMedicationValidator(droneService);
        ErrorModel model = validator.errorDuringMedicationLoad(medication, drone);
        if (model.isValid()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(droneService.addMedicationToDrone(new ModelMapping().fromMedicationToLoad(medication, medicationService), drone));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }
}
