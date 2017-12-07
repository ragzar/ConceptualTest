package com.company.dataaccessobject;

import com.company.domainobject.DriverDO;
import com.company.domainvalue.OnlineStatus;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, DriverRepositoryCustom
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
