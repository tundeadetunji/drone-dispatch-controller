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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
        if (droneRepository.existsById(id)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Drone findById(Long id) {
        if (droneExists(id)){
            return droneRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    @Override
    public Drone addMedicationToDrone(Medication medication, Drone drone) {

        Collection<Medication> medications =  drone.getLoadedMedication();
        Iterator<Medication> iterator = medications.iterator();
        Double loaded = 0.0;
        while(iterator.hasNext()){
            loaded += iterator.next().getWeight();
        }

        //save
        Medication newMedication = medicationRepository.save(medication);

        //set drone state to LOADING if added weights is under drone's weight limit, or LOADED if at drone's weight limit
        Double weightLimit = drone.getWeightLimit();
        Double weight = medication.getWeight() + loaded;
        if (weight >= weightLimit){
            drone.setState(Domain.State.LOADED.toString());
            droneRepository.save(drone);
        }
        else if (weight < weightLimit){
            drone.setState(Domain.State.LOADING.toString());
            droneRepository.save(drone);
        }

        drone.getLoadedMedication().add(newMedication);
        return drone;
    }

    @Override
    public Drone findBySerial(String serial) {
        Drone drone = droneRepository.findBySerial(serial);
        if (drone != null){
            return drone;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    public List<Drone> findWhereLoading() {
        return droneRepository.findWhereLoading();
    }



}
