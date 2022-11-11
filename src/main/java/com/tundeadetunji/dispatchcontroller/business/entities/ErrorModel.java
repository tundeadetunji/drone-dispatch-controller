package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;
import static com.tundeadetunji.dispatchcontroller.business.domain.Domain.DEFAULT_ERROR_MESSAGE;

/**
 * This is the object that is passed back to the user when
 * requesting information and the values passed are invalid or
 * incomplete.
 */
@Data
public class ErrorModel {
    private String message = DEFAULT_ERROR_MESSAGE;
    private ErrorsDuringDroneRegister droneRegisterModel;
    private ErrorsDuringMedicationLoad medicationLoadModel;
    private ErrorsDuringViewDrone viewDroneModel;
    private boolean valid;
}
