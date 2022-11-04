package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import com.tundeadetunji.dispatchcontroller.business.repositories.MedicationRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

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
        return repository.findByName(name);
    }
}
