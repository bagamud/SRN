package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.StreetRoadNetwork;

import java.util.ArrayList;

public interface SrnRepository extends CrudRepository<StreetRoadNetwork, Integer> {
    StreetRoadNetwork findById(int id);
    ArrayList<StreetRoadNetwork> findAllByDepartment_CodeOrderById(int code);
}
