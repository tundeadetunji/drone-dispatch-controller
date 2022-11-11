package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

/**
 * This is the object the user sends in other to view a drone's info,
 * either all the info or only the battery info.
 */
@Data
public class DroneToView {

    private Long id;

}
