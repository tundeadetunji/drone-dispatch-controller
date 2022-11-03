package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.repositories.DroneRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class DroneEncapsulation implements DroneService {
    private final DroneRepository droneRepository;

    @Override
    public Drone saveDrone(Drone drone) {
        return droneRepository.save(drone);
    }
}
