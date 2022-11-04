package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;

public interface MedicationService {
    Medication saveMedication(Medication medication);
    Medication findByName(String name);
}
