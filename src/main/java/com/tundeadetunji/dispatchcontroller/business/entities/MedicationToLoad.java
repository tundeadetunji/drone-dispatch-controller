package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

/**
 * This is the object that is given by the user when
 * loading medication onto drone.
 */

@Data
public class MedicationToLoad {
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String image;
    private Long droneId;
}
