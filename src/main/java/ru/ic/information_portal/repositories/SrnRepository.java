package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.StreetRoadNetwork;

import java.sql.Date;

public interface SrnRepository extends CrudRepository<StreetRoadNetwork, Integer> {
    StreetRoadNetwork findById(int id);

    Iterable<StreetRoadNetwork> findAllByDepartment_CodeOrderById(int code);

    //    int countAll();
    int countAllByResult_Fixed(boolean fixed);

    int countAllByDepartment_RegCode(int regCode);

    int countAllByDepartment_RegCodeAndResult_Fixed(int regCode, boolean fixed);

    int countAllByDepartment_code(int code);

    int countAllByDepartment_codeAndResult_Fixed(int code, boolean fixed);

    int findAllByDepartment_CodeAndFoundDateBetweenAndShortcoming_IdAndMeasures_Id(int department_code, Date foundDate, Date foundDate2, int shortcoming_id, int measures_id);
}