package com.concepttest.dataaccessobject;

import com.concepttest.domainobject.DriverDO;
import com.concepttest.domainvalue.OnlineStatus;

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
