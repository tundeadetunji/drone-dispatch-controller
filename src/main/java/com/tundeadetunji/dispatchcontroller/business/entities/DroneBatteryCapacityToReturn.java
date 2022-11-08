package com.tundeadetunji.dispatchcontroller.business.entities;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class DroneBatteryCapacityToReturn {
    private Long id;
    private String serial;
    private Double batteryCapacity;

}
