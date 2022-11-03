package com.tundeadetunji.dispatchcontroller.business.models.dtos;

import com.tundeadetunji.dispatchcontroller.business.models.dtos.ErrorDuringRegister;
import lombok.Data;

@Data
public class RegisterErrorModel {
    private String message;
    private ErrorDuringRegister model;
    private boolean valid;
}
