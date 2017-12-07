package com.company.dataaccessobject;

import java.util.List;

import com.company.domainobject.DriverDO;
import com.company.util.SearchCriteria;

public interface DriverRepositoryCustom
{
    public List<DriverDO> searchByCarProperties(List<SearchCriteria> params);

}
