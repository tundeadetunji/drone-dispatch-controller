package com.tundeadetunji.dispatchcontroller.business.domain;

public class Domain {
    public enum Model{
        Lightweight, Middleweight, Cruiserweight, Heavyweight
    }
    public enum State{
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }
    public static final String DEFAULT_ERROR_MESSAGE = "One or more details was not supplied or is invalid!";
    public static final String MIDDLE_WEIGHT = Domain.Model.Middleweight.toString();
    public static final String CRUISER_WEIGHT = Domain.Model.Cruiserweight.toString();
    public static final String HEAVY_WEIGHT = Domain.Model.Heavyweight.toString();
    public static final String LIGHT_WEIGHT = Domain.Model.Lightweight.toString();

}
