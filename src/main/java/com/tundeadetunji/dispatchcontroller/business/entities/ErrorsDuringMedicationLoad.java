package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

/**
 * This object traps the invalid/incomplete values the user
 * enters when attempting to load medication onto a drone.
 * This object is attached to the ErrorModel object that is
 * passed back to the user.
 */

@Data
public class ErrorsDuringMedicationLoad {
    private String name;
    private String weight;
    private String code;
    private String image;
    private String droneId;
    private String batteryCapacity;
}
