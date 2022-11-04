package com.tundeadetunji.dispatchcontroller.business.entities;

import lombok.Data;

@Data
public class ErrorsDuringDroneRegister {
    private String serial;
    private String model;
    private String weightLimit;
}
