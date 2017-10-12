package com.concepttest.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.concepttest.domainobject.CarDO;

public interface CarRepository extends CrudRepository<CarDO, Long>
{

}
