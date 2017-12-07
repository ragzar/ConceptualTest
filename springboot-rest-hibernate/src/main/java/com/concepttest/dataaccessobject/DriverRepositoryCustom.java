package com.concepttest.dataaccessobject;

import java.util.List;

import com.concepttest.domainobject.DriverDO;
import com.concepttest.util.SearchCriteria;

public interface DriverRepositoryCustom
{
    public List<DriverDO> searchByCarProperties(List<SearchCriteria> params);

}
