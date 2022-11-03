package com.tundeadetunji.dispatchcontroller.business.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, length = 4000)
    private String image;
}