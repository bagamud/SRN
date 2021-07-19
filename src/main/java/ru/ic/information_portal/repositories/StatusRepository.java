package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    Status findById(int id);
}
