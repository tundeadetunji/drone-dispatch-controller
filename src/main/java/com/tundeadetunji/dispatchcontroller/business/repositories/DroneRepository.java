package com.tundeadetunji.dispatchcontroller.business.repositories;

import com.tundeadetunji.dispatchcontroller.business.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

}
