package com.concepttest.dataaccessobject;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.concepttest.domainobject.CarDO;
import com.concepttest.domainobject.DriverDO;
import com.concepttest.util.SearchCriteria;

@Repository
public class DriverRepositoryImpl implements DriverRepositoryCustom
{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<DriverDO> searchByCarProperties(List<SearchCriteria> params)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarDO> query = builder.createQuery(CarDO.class);
        Root<CarDO> r = query.from(CarDO.class);

        Predicate predicate = builder.conjunction();

        for (SearchCriteria param : params)
        {
            if (param.getValue() == null)
            {
                continue;
            }

            if (r.get(param.getKey()).getJavaType() == String.class)
            {
                predicate =
                    builder.and(
                        predicate,
                        builder.like(
                            r.get(param.getKey()),
                            "%" + param.getValue() + "%"));
            }
            else
            {
                predicate =
                    builder.and(
                        predicate,
                        builder.equal(r.get(param.getKey()), param.getValue()));
            }
        }
        query.where(predicate);

        List<CarDO> result = entityManager.createQuery(query).getResultList();
        List<DriverDO> driverResult =
            result
                .stream().map(CarDO::getDriverCar)
                .collect(Collectors.toList());

        return driverResult;
    }

}
