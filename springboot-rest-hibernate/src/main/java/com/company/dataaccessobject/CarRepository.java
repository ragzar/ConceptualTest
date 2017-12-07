package com.company.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.company.domainobject.CarDO;

public interface CarRepository extends CrudRepository<CarDO, Long>
{

}
