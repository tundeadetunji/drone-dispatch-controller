package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import com.tundeadetunji.dispatchcontroller.business.repositories.MedicationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MedicationServiceImplementation implements MedicationService{

    private final MedicationRepository repository;

    @Override
    public Medication saveMedication(Medication medication) {
        return repository.save(medication);
    }

    @Override
    public Medication findByName(String name) {
        Medication medication = repository.findByName(name);
        if (medication != null){
            return medication;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Medication> getMedications() {
        return repository.findAll();
    }
}
