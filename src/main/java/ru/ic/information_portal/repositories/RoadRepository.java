package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Road;

public interface RoadRepository extends CrudRepository<Road, Integer> {
}