package com.tundeadetunji.dispatchcontroller.business.repositories;

import com.tundeadetunji.dispatchcontroller.business.models.DroneBatteryCapacityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryCapacityLogRepository extends JpaRepository<DroneBatteryCapacityLog, Long> {
}
