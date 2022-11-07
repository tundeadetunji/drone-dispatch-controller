package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.domain.Domain;
import com.tundeadetunji.dispatchcontroller.business.entities.DroneToView;
import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import com.tundeadetunji.dispatchcontroller.business.repositories.DroneRepository;
import com.tundeadetunji.dispatchcontroller.business.repositories.MedicationRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Data
@Service
@Slf4j
@Transactional
public class DroneServiceImplementation implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public Drone saveDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public boolean droneExists(Long id) {
        return droneRepository.existsById(id);
    }

    @Override
    public Drone findById(Long id) {
        return droneRepository.findById(id).get();
    }

    @Override
    public boolean addMedicationToDrone(Medication medication, Drone drone) {
        //set drone state to LOADING
        drone.setState(Domain.State.LOADING.toString());
        droneRepository.save(drone);

        //save
        Medication newMedication = medicationRepository.save(medication);
        return drone.getLoadedMedication().add(newMedication);
    }

    @Override
    public Drone findBySerial(String serial) {
        return droneRepository.findBySerial(serial);
    }

    @Override
    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    /*@Override
    public List<Drone> findAll() {
        return repository.findAll();
    }*/

    /*@Override
    public boolean droneExistsBySerial(String serial) {
        return repository.findBySerial(serial) != null;
    }*/


}
