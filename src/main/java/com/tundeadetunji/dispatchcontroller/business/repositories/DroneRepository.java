package com.tundeadetunji.dispatchcontroller.business.repositories;

import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("select u from Drone u where u.serial = :serial")
    Drone findBySerial(@Param("serial") String serial);
}
