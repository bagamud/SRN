package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Result;
import ru.ic.information_portal.entity.StreetRoadNetwork;

public interface SrnRepository extends CrudRepository<StreetRoadNetwork, Integer> {
    StreetRoadNetwork findById(int id);

    Iterable<StreetRoadNetwork> findAllByDepartment_CodeOrderById(int code);

    int countAllByDepartment_CodeAndResult_IdIsLike(int department_code, int result_id);
}
