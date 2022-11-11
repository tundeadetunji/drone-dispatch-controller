package com.tundeadetunji.dispatchcontroller.business.entities;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is the object that is returned when user requests
 * battery capacity for given drone.
 */

@Data
public class DroneBatteryCapacityToReturn {
    private Long id;
    private String serial;
    private Double batteryCapacity;

}
