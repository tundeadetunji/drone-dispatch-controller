package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

/**
 * This object traps the invalid/incomplete values the user
 * enters when attempting to register a drone.
 * This object is attached to the ErrorModel object that is
 * passed back to the user.
 */
@Data
public class ErrorsDuringDroneRegister {
    private String serial;
    private String model;
    private String weightLimit;
}
