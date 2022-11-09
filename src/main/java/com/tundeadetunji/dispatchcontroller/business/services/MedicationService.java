package com.tundeadetunji.dispatchcontroller.business.services;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface MedicationService {
    Medication saveMedication(Medication medication);
    Medication findByName(String name);
    List<Medication> getMedications();
}
