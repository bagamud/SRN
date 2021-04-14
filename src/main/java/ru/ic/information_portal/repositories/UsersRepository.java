package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Users findByUsername(String username);

}
