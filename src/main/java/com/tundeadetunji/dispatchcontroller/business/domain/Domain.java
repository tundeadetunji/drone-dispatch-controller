package com.tundeadetunji.dispatchcontroller.business.domain;

public class Domain {
    public enum Model{
        Lightweight, Middleweight, Cruiserweight, Heavyweight
    }
    public enum State{
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }
}
