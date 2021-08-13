package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.StreetRoadNetwork;

import java.sql.Date;

public interface SrnRepository extends CrudRepository<StreetRoadNetwork, Integer> {
    StreetRoadNetwork findById(int id);

    Iterable<StreetRoadNetwork> findAllByDepartment_CodeOrderById(int code);

    Iterable<StreetRoadNetwork> findAllByCreateDateAfterOrderById(Date createDate);

    Iterable<StreetRoadNetwork> findAllByDepartment_CodeAndCreateDateAfterOrderById(int code, Date createDate);

    long countByStatus_Fixed(boolean fixed);

    long countByDepartment_RegCode(int regCode);

    long countByDepartment_RegCodeAndStatus_Fixed(int regCode, boolean fixed);

    long countByDepartment_code(int code);

    long countByDepartment_codeAndStatus_Fixed(int code, boolean fixed);

    int findAllByDepartment_CodeAndFoundDateBetweenAndShortcoming_IdAndMeasures_Id(int department_code, Date foundDate, Date foundDate2, int shortcoming_id, int measures_id);
}