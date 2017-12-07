package com.concepttest.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.concepttest.domainobject.MaintenanceDO;
import com.concepttest.domainvalue.MaintenanceStatus;

/**
 * Database Access Object for maintenance table.
 * <p/>
 */
public interface MaintenanceRepository extends CrudRepository<MaintenanceDO, Long>
{

    List<MaintenanceDO> findByMaintenanceStatus(MaintenanceStatus maintenanceStatus);
    
    List<MaintenanceDO> findByCarId(Long carId);
}
