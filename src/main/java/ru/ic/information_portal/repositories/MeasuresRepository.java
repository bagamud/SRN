package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Measures;

public interface MeasuresRepository extends CrudRepository<Measures, Integer> {
}
