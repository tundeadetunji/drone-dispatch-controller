package com.tundeadetunji.dispatchcontroller.web.controllers;

import com.tundeadetunji.dispatchcontroller.business.entities.*;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.services.DroneServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.services.MedicationServiceImplementation;
import com.tundeadetunji.dispatchcontroller.business.utility.LoadMedicationValidator;
import com.tundeadetunji.dispatchcontroller.business.utility.ModelMapping;
import com.tundeadetunji.dispatchcontroller.business.utility.RegisterDroneValidator;
import com.tundeadetunji.dispatchcontroller.business.utility.ViewDroneValidator;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@Data
public class APIController {

    private final DroneServiceImplementation droneService;
    private final MedicationServiceImplementation medicationService;


    //register a drone
    @PostMapping("/register")
    public ResponseEntity<Object> RegisterDrone(@RequestBody DroneToRegister droneToRegister) {
        //validate the inputs and determine if all are valid
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
        //validate the inputs and determine if all are valid
        ViewDroneValidator validator = new ViewDroneValidator(droneService);
        ErrorModel model = validator.errorDuringViewDrone(drone);

        if (model.isValid()){
            return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findById(drone.getId()));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    //view battery capacity of drone (finds it by id)
    @GetMapping("/battery")
    public ResponseEntity<Object> getDroneBatteryCapacity(@RequestBody DroneToView drone){
        //validate the inputs and determine if all are valid
        ViewDroneValidator validator = new ViewDroneValidator(droneService);
        ErrorModel model = validator.errorDuringViewDrone(drone);

        if (model.isValid()){
            return ResponseEntity.status(HttpStatus.FOUND).body(new ModelMapping().fromDroneBatterCapacityToReturn(drone, droneService));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    //view drones that are in LOADING or IDLE state
    @GetMapping("/available")
    public ResponseEntity<Object> getLoadingDrones(){
        return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findWhereLoading());
    }


    //load a drone with medication item
    @PostMapping("/load")
    public ResponseEntity<Object> LoadDrone(@RequestBody MedicationToLoad medication) {
        //validate the inputs and determine if all are valid
        Drone drone = droneService.findById(medication.getDroneId());
        LoadMedicationValidator validator = new LoadMedicationValidator(droneService);
        ErrorModel model = validator.errorDuringMedicationLoad(medication, drone);

        if (model.isValid()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(droneService.addMedicationToDrone(new ModelMapping().fromMedicationToLoad(medication, medicationService), drone));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    //view a drone's list of (loaded) medication
    @GetMapping("/loaded")
    public ResponseEntity<Object> checkLoadedMedication(@RequestBody DroneToView drone){
        //validate the inputs and determine if all are valid
        ViewDroneValidator validator = new ViewDroneValidator(droneService);
        ErrorModel model = validator.errorDuringViewDrone(drone);

        if (model.isValid()){
            return ResponseEntity.status(HttpStatus.FOUND).body(droneService.findById(drone.getId()).getLoadedMedication());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


    @GetMapping("/medications")
    public ResponseEntity<Object> getSavedMedications(){
        List<MedicationToReturn> list = new ModelMapping().fromMedicationToReturn(medicationService.getMedications());
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
}
