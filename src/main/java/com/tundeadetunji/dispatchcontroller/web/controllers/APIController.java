package com.tundeadetunji.dispatchcontroller.web.controllers;

import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.DroneToRegister;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.ErrorDuringRegister;
import com.tundeadetunji.dispatchcontroller.business.services.DroneEncapsulation;
import com.tundeadetunji.dispatchcontroller.business.services.ModelMapping;
import com.tundeadetunji.dispatchcontroller.business.models.dtos.RegisterErrorModel;
import com.tundeadetunji.dispatchcontroller.business.services.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class APIController {

    private final DroneEncapsulation service;

    public APIController(DroneEncapsulation service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Object> welcome(){
        return ResponseEntity.ok().body("done");
    }

    //return ResponseEntity.status(HttpStatus.CREATED).body(model.getModel());
    //return ResponseEntity.ok().body("error");

    @PostMapping("/register")
    public ResponseEntity<Object> RegisterDrone(@RequestBody DroneToRegister droneToRegister){
        Validation validation = new Validation();
        RegisterErrorModel model = validation.errorDuringRegister(droneToRegister);
        if (model.isValid()){
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveDrone(new ModelMapping().FromDroneToRegister(droneToRegister)));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        }
    }


}
