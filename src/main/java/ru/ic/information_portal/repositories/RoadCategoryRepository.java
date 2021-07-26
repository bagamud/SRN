package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.RoadCategory;

public interface RoadCategoryRepository extends CrudRepository<RoadCategory, Integer> {
}