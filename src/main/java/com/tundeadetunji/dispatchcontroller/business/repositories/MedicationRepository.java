package com.tundeadetunji.dispatchcontroller.business.repositories;

import com.tundeadetunji.dispatchcontroller.business.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("select u from Medication u where u.name = :name")
    Medication findByName(@Param("name") String name);
}
