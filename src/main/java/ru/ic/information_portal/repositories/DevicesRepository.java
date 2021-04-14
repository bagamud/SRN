package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Devices;

public interface DevicesRepository extends CrudRepository<Devices, Integer> {
}
