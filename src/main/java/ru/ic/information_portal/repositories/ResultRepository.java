package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Result;

public interface ResultRepository extends CrudRepository<Result, Integer> {
    Result findById(int id);
}
