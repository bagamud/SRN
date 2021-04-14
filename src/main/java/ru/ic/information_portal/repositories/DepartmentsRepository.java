package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Department;

public interface DepartmentsRepository extends CrudRepository<Department, Integer> {
}
