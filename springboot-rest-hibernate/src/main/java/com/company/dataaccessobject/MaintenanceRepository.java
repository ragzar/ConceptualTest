package com.company.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.domainobject.MaintenanceDO;
import com.company.domainvalue.MaintenanceStatus;

/**
 * Database Access Object for maintenance table.
 * <p/>
 */
public interface MaintenanceRepository extends CrudRepository<MaintenanceDO, Long>
{

    List<MaintenanceDO> findByMaintenanceStatus(MaintenanceStatus maintenanceStatus);
    
    List<MaintenanceDO> findByCarId(Long carId);
}
