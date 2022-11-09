package com.tundeadetunji.dispatchcontroller.business.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, columnDefinition = "CHARACTER LARGE OBJECT")
    private String image;

    @Column(nullable = false)
    private Long droneId;
}
