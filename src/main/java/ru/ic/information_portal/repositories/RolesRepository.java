package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Role;

public interface RolesRepository extends CrudRepository<Role, Integer> {
}
