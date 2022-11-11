package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * This is the object that is returned when user requests
 * information about a medication.
 */

@Data
public class MedicationToReturn {
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String image;
}
